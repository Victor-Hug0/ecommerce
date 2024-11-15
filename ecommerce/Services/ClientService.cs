using ecommerce.Database;
using ecommerce.DTO;
using ecommerce.Models;
using Microsoft.AspNetCore.Http.HttpResults;
using Microsoft.AspNetCore.Identity;
using Microsoft.EntityFrameworkCore;
using Microsoft.IdentityModel.Tokens;
using NuGet.Protocol;
using System.IdentityModel.Tokens.Jwt;
using System.Security.Claims;
using System.Text;
using System.Xml.Linq;

namespace ecommerce.Services {
    public class ClientService {
        private readonly ApplicationDbContext _context;
        private readonly IPasswordHasher<Client> _passwordHasher;
        private readonly IConfiguration _configuration;
        private readonly IHttpContextAccessor _contextAccessor;

        public ClientService(ApplicationDbContext context, IPasswordHasher<Client> passwordHasher, IConfiguration configuration, IHttpContextAccessor contextAccessor) {
            _context = context;
            _passwordHasher = passwordHasher;
            _configuration = configuration;
            _contextAccessor = contextAccessor;
        }

        public async Task<Client> CreateClient(CreateClientRequestDTO clientDTO) {

            bool emailExists = await _context.Clients.AnyAsync(c => c.Email == clientDTO.Email);
            if (emailExists) {
                return null;
            }

            var client = new Client(clientDTO.Name, clientDTO.Email, clientDTO.PhoneNumber);
            client.Password = _passwordHasher.HashPassword(client, clientDTO.Password);

            _context.Clients.Add(client);
            await _context.SaveChangesAsync();
            return client;
        }

        public async Task<string> Login(LoginRequestDTO loginDTO) {
            var client = await _context.Clients.SingleOrDefaultAsync(e => e.Email == loginDTO.Email);

            if (client == null || _passwordHasher.VerifyHashedPassword(client, client.Password, loginDTO.Password) != PasswordVerificationResult.Success) {
                throw new UnauthorizedAccessException("Invalid email or password.");
            }

            var tokenHandler = new JwtSecurityTokenHandler();
            var key = Encoding.UTF8.GetBytes(_configuration["Jwt:Key"]);
            var tokenDescriptor = new SecurityTokenDescriptor {
                Subject = new ClaimsIdentity(new Claim[]
            {
                new Claim(ClaimTypes.NameIdentifier, client.Id.ToString()),
                new Claim(ClaimTypes.Name, client.Email)
            }),
                Expires = DateTime.UtcNow.AddHours(2),
                Issuer = _configuration["Jwt:Issuer"],
                Audience = _configuration["Jwt:Audience"],
                SigningCredentials = new SigningCredentials(new SymmetricSecurityKey(key), SecurityAlgorithms.HmacSha256Signature)
            };
            var token = tokenHandler.CreateToken(tokenDescriptor);
            return tokenHandler.WriteToken(token); 
        }

        public async Task<List<ClientResponseDTO>> GetAllClients() {
            var clients = await _context.Clients
                .Include(c => c.Orders)
                .Include(c => c.Reviews)
                .ToListAsync();

            var clientDTOs = new List<ClientResponseDTO>();

            foreach (var client in clients) {
                var ordersDTO = client.Orders.Select(order => new OrderDTO(
                    order.Id,
                    order.OrderDate,
                    order.OrderStatus,
                    order.Discount,
                    order.TotalValue
                )).ToList();

                var reviewsDTO = client.Reviews.Select(review => new ReviewDTO(
                    review.Id,
                    review.Rating,
                    review.Comment,
                    review.ReviewDate
                )).ToList();

                var clientDTO = new ClientResponseDTO(
                    client.Id,
                    client.Name,
                    client.Email,
                    ordersDTO,
                    reviewsDTO
                );

                clientDTOs.Add(clientDTO);
            }

            return clientDTOs;
        }



        public async Task<ClientResponseDTO> GetClientById(long id) {
            var client = await _context.Clients
                .Include(c => c.Orders)
                .Include(c => c.Reviews)
                .FirstOrDefaultAsync(c => c.Id == id);

            if (client == null) {
                return null;  
            }

            var ordersDTO = client.Orders.Select(order => new OrderDTO(
                order.Id,
                order.OrderDate,
                order.OrderStatus,
                order.Discount,
                order.TotalValue
            )).ToList();

            var reviewsDTO = client.Reviews.Select(review => new ReviewDTO(
                review.Id,
                review.Rating,
                review.Comment,
                review.ReviewDate
            )).ToList();

            return new ClientResponseDTO(
                client.Id,
                client.Name,
                client.Email,
                ordersDTO,
                reviewsDTO
            );
        }

        public async Task<Client> UpdateClient(UpdateClientRequestDTO clientDTO, long id) {
            var client = await _context.Clients.FindAsync(id) ?? throw new KeyNotFoundException("Client not found");
            client.Name = clientDTO.Name ?? client.Name; 
            client.Email = clientDTO.Email ?? client.Email;
            client.PhoneNumber = clientDTO.PhoneNumber ?? client.PhoneNumber;

            await _context.SaveChangesAsync();
            return client;
        }

        public async Task<bool> DeleteClient(long id) {
            var client = await _context.Clients.FindAsync(id) ?? throw new KeyNotFoundException("Client not found");

            _context.Clients.Remove(client);
            await _context.SaveChangesAsync();
            return true;
        }

        public async Task<Review> CreateReview(ReviewProductRequestDTO reviewDTO) {
            var userId = _contextAccessor.HttpContext.User.FindFirstValue(ClaimTypes.NameIdentifier);

            if (string.IsNullOrEmpty(userId)) {
                throw new UnauthorizedAccessException("Usuário não autenticado.");
            }

            long clientId = long.Parse(userId);

            var product = await _context.Products.FindAsync(reviewDTO.ProductId)
                ?? throw new KeyNotFoundException("Produto não encontrado.");

            // Verificar se o cliente comprou o produto (pedido não pendente)
            bool hasPurchased = await _context.Orders
                .Include(o => o.Items)
                .Where(o => o.ClientId == clientId && o.OrderStatus != OrderStatus.Pending)
                .AnyAsync(o => o.Items.Any(i => i.Id == reviewDTO.ProductId));

            if (!hasPurchased) {
                throw new InvalidOperationException("Você precisa comprar o produto antes de avaliá-lo.");
            }

            // Verificar se o produto está em um pedido com status Pending
            bool isPending = await _context.Orders
                .Where(o => o.ClientId == clientId && o.OrderStatus == OrderStatus.Pending)
                .AnyAsync(o => o.Items.Any(i => i.Id == reviewDTO.ProductId));

            if (isPending) {
                throw new InvalidOperationException("Não é possível avaliar um produto de um pedido ainda pendente.");
            }

            var review = new Review(
                reviewDTO.ProductId,
                clientId,
                reviewDTO.Rating,
                reviewDTO.Comment
            );

            _context.Reviews.Add(review);
            await _context.SaveChangesAsync();

            return review;
        }

    }
}

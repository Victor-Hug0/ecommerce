using ecommerce.Database;
using ecommerce.DTO;
using ecommerce.Models;
using Microsoft.AspNetCore.Http.HttpResults;
using Microsoft.EntityFrameworkCore;

namespace ecommerce.Services {
    public class ClientService {
        private readonly ApplicationDbContext _context;
        
        public ClientService(ApplicationDbContext context) { 
            _context = context;
        }

        public async Task<Client> CreateClient(CreateClientRequestDTO clientDTO) {
            var client = new Client(clientDTO.Name, clientDTO.Email, clientDTO.PhoneNumber);

            _context.Clients.Add(client);
            await _context.SaveChangesAsync();
            return client;
        }

        public async Task<Client> GetClientById(int id) {
            return await _context.Clients.FindAsync(id);
        }

        public async Task<List<Client>> GetAllClients() {
            return await _context.Clients.ToListAsync();
        }

        public async Task<Client> UpdateClient(UpdateClientRequestDTO clientDTO, int id) {
            var client = await _context.Clients.FindAsync(id) ?? throw new KeyNotFoundException("Client not found");
            client.Name = clientDTO.Name ?? client.Name; 
            client.Email = clientDTO.Email ?? client.Email;
            client.PhoneNumber = clientDTO.PhoneNumber ?? client.PhoneNumber;

            await _context.SaveChangesAsync();
            return client;
        }

        public async Task<bool> DeleteClient(int id) {
            var client = await _context.Clients.FindAsync(id) ?? throw new KeyNotFoundException("Client not found");

            _context.Clients.Remove(client);
            await _context.SaveChangesAsync();
            return true;
        }
    }
}

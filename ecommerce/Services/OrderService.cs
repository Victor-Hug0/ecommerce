using ecommerce.Database;
using ecommerce.DTO;
using ecommerce.Models;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using System.Security.Claims;

namespace ecommerce.Services {
    public class OrderService {
        private readonly ApplicationDbContext _context;
        private readonly IHttpContextAccessor _contextAccessor;
        private readonly ProductService _productService;

        public OrderService(ApplicationDbContext context, IHttpContextAccessor contextAccessor, ProductService productService) {
            _context = context;
            _contextAccessor = contextAccessor;
            _productService = productService;
            _productService = productService;
        }

        public async Task<Order> CreateOrder(CreateOrderRequestDTO orderDTO) {

            var userId = _contextAccessor.HttpContext.User.FindFirstValue(ClaimTypes.NameIdentifier);

            if (userId == null || long.Parse(userId) != orderDTO.ClientId) {
                throw new UnauthorizedAccessException("Você não está autorizado a fazer este pedido.");
            }

            var shippingAddress = new ShippingAddress(
                orderDTO.ShippingAddress.Street,
                orderDTO.ShippingAddress.Number,
                orderDTO.ShippingAddress.City,
                orderDTO.ShippingAddress.State,
                orderDTO.ShippingAddress.ZipCode,
                orderDTO.ShippingAddress.Complement
            );

            decimal totalValue = decimal.Zero;
            decimal discount = decimal.Zero;
            var items = new List<OrderItem>();

            foreach (var itemDTO in orderDTO.OrderItems) {
                var product = await _context.Products.FindAsync(itemDTO.ProductId) ?? throw new KeyNotFoundException($"Product with Id {itemDTO.ProductId} not found.");
                bool haveStock = await _productService.AttStock(itemDTO.ProductId, itemDTO.Quantity);

                if (!haveStock) {
                    throw new InvalidOperationException("Insufficient stock.");
                }

                decimal subTotal = product.Price * itemDTO.Quantity;
                totalValue += subTotal;

                var orderItem = new OrderItem(
                    itemDTO.ProductId,
                    itemDTO.Quantity,
                    product.Price
                );

                items.Add(orderItem);
            }

            _context.ShippingAddresses.Add(shippingAddress);
            await _context.SaveChangesAsync();

            var order = new Order(
                orderDTO.ClientId,
                shippingAddress.Id,
                discount,
                totalValue
            );

            order.Items = items;

            foreach (var item in items) {
                item.OrderId = order.Id;  
            }

            _context.Orders.Add(order);
            await _context.SaveChangesAsync();

            await _context.SaveChangesAsync();

            return order;
        }

        public async Task<IActionResult> PayOrder(long orderId, PaymentDTO paymentDTO) {
            var userIdClaim = _contextAccessor.HttpContext?.User.FindFirst(ClaimTypes.NameIdentifier);
            if (userIdClaim == null) {
                return new UnauthorizedResult();
            }

            long userId = long.Parse(userIdClaim.Value);

            var order = await _context.Orders.FindAsync(orderId);

            if (order.ClientId != userId) {
                return new UnauthorizedResult();
            }

            if (order == null) {
                return new NotFoundObjectResult("Pedido não encontrado.");
            }

            if (order.OrderStatus != OrderStatus.Pending) {
                return new BadRequestObjectResult("O pedido não está em um estado válido para pagamento.");
            }

            order.OrderStatus = OrderStatus.Paid;

            var payment = new Payment(
                orderId,
                paymentDTO.Method,
                PaymentStatus.Paid,
                0,
                order.TotalValue
            );

            _context.Payments.Add(payment);
            await _context.SaveChangesAsync();

            return new OkObjectResult(new { Message = "Pagamento realizado com sucesso!" });
        }
    }
}

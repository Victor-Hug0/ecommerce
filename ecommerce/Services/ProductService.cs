using ecommerce.Database;
using ecommerce.DTO;
using ecommerce.Models;
using Microsoft.EntityFrameworkCore;

namespace ecommerce.Services {
    public class ProductService {
        private readonly ApplicationDbContext _context;

        public ProductService(ApplicationDbContext context) {
            _context = context;
        }

        public async Task<Product> CreateProduct(CreateProductRequestDTO productDTO) {
            
            var vendor = await _context.Vendors.AnyAsync(v=> v.Id == productDTO.VendorId);
            if (!vendor) {
                throw new KeyNotFoundException("The specified vendor does not exist.");
            }

            var product = new Product(
                productDTO.Name,
                productDTO.Description,
                productDTO.Price,
                productDTO.StockQuantity,
                productDTO.Price * productDTO.StockQuantity,
                productDTO.Category,
                productDTO.VendorId
            );

            _context.Products.Add(product);
            await _context.SaveChangesAsync();
            return product;
        }

        public async Task<List<Product>> GetAllProducts() {
            return await _context.Products.ToListAsync();
        }

        public async Task<Product> GetProductById(long id) {
            return await _context.Products.FindAsync(id);
        }

        public async Task<List<Product>> GetAllVendorProducts(long id) {
            bool vendorExists = await _context.Vendors.AnyAsync(v => v.Id == id);
            if (!vendorExists) {
                throw new KeyNotFoundException("The specified vendor does not exist.");
            }

            return await _context.Products
                .Where(v => v.VendorId == id)       
                .ToListAsync();
        }

        public async Task<bool> DeleteProduct(long id) {
            var product = await _context.Products.FindAsync(id);

            if (product == null) {
                return false;
            }

            _context.Products.Remove(product);
            await _context.SaveChangesAsync();
            return true;
        }

        public async Task<bool> AttStock(long productId, int quantity) {
            var product = await GetProductById(productId) ?? throw new KeyNotFoundException($"Product with Id {productId} not found.");

            if (product.StockQuantity < quantity) {
                return false;
            }

            product.StockQuantity -= quantity;
            product.StockPrice = product.StockQuantity * product.Price;
            await _context.SaveChangesAsync();
            return true;  
        }
    }
}

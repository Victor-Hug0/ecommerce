using ecommerce.DTO;
using ecommerce.Models;
using ecommerce.Services;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;

namespace ecommerce.Controllers {
    [Route("api/product")]
    [ApiController]
    public class ProductController : ControllerBase {
        public readonly ProductService _productService;

        public ProductController(ProductService productService) { 
            _productService = productService;
        }

        [HttpPost]
        public async Task<ActionResult<Product>> CreateProduct([FromBody] CreateProductRequestDTO productDTO) {
            var product = await _productService.CreateProduct(productDTO);

            return CreatedAtAction(nameof(GetProductById), new { id = product.Id }, product);
        }

        [HttpGet("{id}")]
        public async Task<ActionResult<Product>> GetProductById(long id) {
            var product = await _productService.GetProductById(id);
            if (product == null) { 
                return NotFound();
            }

            return Ok(product);
        }

        [HttpGet]
        public async Task<ActionResult<Product>> GetAllProducts() {
            return Ok(await _productService.GetAllProducts());
        }

        [HttpGet("vendor/{id}")]
        public async Task<ActionResult<Product>> GetAllVendorProducts(long id) {
            return Ok(await _productService.GetAllVendorProducts(id));
        }

        [HttpDelete("{id}")]
        public async Task<IActionResult> DeleteProduct(long id) {
            var deleted = await _productService.DeleteProduct(id);
            if (!deleted) {
                throw new KeyNotFoundException("The specified product does not exist.");
            }

            return NoContent();
        }
    }
}

using ecommerce.Models;
using System.ComponentModel.DataAnnotations;

namespace ecommerce.DTO {
    public class CreateProductRequestDTO {
        [Required]
        public string Name { get; set; }
        [Required]
        public string Description { get; set; }
        [Required]
        [Range(0.01, double.MaxValue, ErrorMessage = "Price must be greater than zero.")]
        public decimal Price { get; set; }
        [Required]
        [Range(1, int.MaxValue, ErrorMessage = "StockQuantity must be at least 1.")]
        public int StockQuantity { get; set; }
        [Required]
        public Category Category { get; set; }
        [Required]
        public long VendorId { get; set; }
    }
}

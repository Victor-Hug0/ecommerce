using Microsoft.Build.Framework;
using System.ComponentModel.DataAnnotations;

namespace ecommerce.DTO {
    public class UpdateProductRequestDTO {
        public string Name { get; set; }       
        public string Description { get; set; }
        [Range(0.01, double.MaxValue, ErrorMessage = "Price must be greater than zero.")]
        public decimal Price { get; set; }
        
    }
}

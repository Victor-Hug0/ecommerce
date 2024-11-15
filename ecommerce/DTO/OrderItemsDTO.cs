using System.ComponentModel.DataAnnotations;

namespace ecommerce.DTO {
    public class OrderItemsDTO {
        [Required]
        public long ProductId { get; set; }
        [Required]
        [Range(1, int.MaxValue, ErrorMessage = "Quantity must be at least 1.")]
        public int Quantity { get; set; }
    }
}

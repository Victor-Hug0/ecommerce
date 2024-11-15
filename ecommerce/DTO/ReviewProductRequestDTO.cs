using System.ComponentModel.DataAnnotations;

namespace ecommerce.DTO {
    public class ReviewProductRequestDTO {
        [Required]
        public long ProductId { get; set; }
        [Required]
        [Range(1, 5, ErrorMessage = "Rating must be between 1 and 5.")]
        public int Rating { get; set; }
        [Required]
        public string Comment { get; set; }
    }
}

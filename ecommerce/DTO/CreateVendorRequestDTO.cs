using System.ComponentModel.DataAnnotations;

namespace ecommerce.DTO {
    public class CreateVendorRequestDTO {
        [Required]
        public string Name { get; set; }
        [Required]
        [EmailAddress]
        public string Email { get; set; }
        [Required]
        [Phone]
        public string PhoneNumber { get; set; }
    }
}

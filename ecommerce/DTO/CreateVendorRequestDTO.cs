using System.ComponentModel.DataAnnotations;

namespace ecommerce.DTO {
    public class CreateVendorRequestDTO {
        [Required]
        public string Name;
        [Required]
        [EmailAddress]
        public string Email;
        [Required]
        [Phone]
        public string PhoneNumber;
    }
}

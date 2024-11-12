using System.ComponentModel.DataAnnotations;

namespace ecommerce.DTO {
    public class UpdateVendorRequestDTO {
        public string Name { get; set; }
        [EmailAddress]
        public string Email { get; set; }
        [Phone]
        public string PhoneNumber { get; set; }
    
    }
}

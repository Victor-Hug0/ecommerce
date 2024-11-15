using System.ComponentModel.DataAnnotations;

namespace ecommerce.DTO {
    public class CreateClientRequestDTO {
        [Required]
        public string Name { get; set; }
        [Required]
        [EmailAddress]
        public string Email {  get; set; }
        [Required]
        [MinLength(6)]
        public string Password { get; set; }
        [Required]
        [Phone]
        public string PhoneNumber { get; set; }
    }
}

using System.ComponentModel.DataAnnotations;

namespace ecommerce.DTO {
    public class ShippingAddressDTO {
        [Required]
        public string Street { get; set; }
        [Required]
        public string Number { get; set; }
        [Required]
        public string City { get; set; }
        [Required]
        public string State { get; set; }
        [Required]
        public string ZipCode { get; set; }
        [Required]
        public string Complement { get; set; }
    }
}

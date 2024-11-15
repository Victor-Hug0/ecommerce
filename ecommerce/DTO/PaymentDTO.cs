using ecommerce.Models;
using System.ComponentModel.DataAnnotations;

namespace ecommerce.DTO {
    public class PaymentDTO {
        [Required]
        public string Method { get; set; }
    }
}

using Microsoft.Build.Framework;

namespace ecommerce.DTO {
    public class CreateOrderRequestDTO {
        [Required]
        public long ClientId { get; set; }
        [Required]
        public ShippingAddressDTO ShippingAddress { get; set; }
        [Required]
        public List<OrderItemsDTO> OrderItems { get; set; }
    }
}

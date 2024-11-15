using System.ComponentModel.DataAnnotations.Schema;
using System.Text.Json.Serialization;

namespace ecommerce.Models {
    [Table("orders")]
    public class Order {
        public long Id { get; set; }
        public long ClientId { get; set; }
        public DateTime OrderDate { get; set; } 
        public OrderStatus OrderStatus { get; set; } 
        public long ShippingAddressId { get; set; }
        public decimal Discount { get; set; } = decimal.Zero;
        public decimal TotalValue { get; set; }
        public virtual Client Client { get; set; }
        public virtual ShippingAddress ShippingAddress { get; set; }
        public virtual ICollection<OrderItem> Items { get; set; }

        public Order() {
            Items = new List<OrderItem>();
            OrderDate = DateTime.UtcNow;
            OrderStatus = OrderStatus.Pending;
        }

        public Order(long clientId, long shippingAddressId, decimal discount, decimal totalValue)
            : this() {
            ClientId = clientId;
            ShippingAddressId = shippingAddressId;
            Discount = discount;
            TotalValue = totalValue;
        }
    }
}

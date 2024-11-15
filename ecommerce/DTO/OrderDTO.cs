using ecommerce.Models;

namespace ecommerce.DTO {
    public class OrderDTO {
        public long Id { get; set; }
        public DateTime OrderDate { get; set; }
        public OrderStatus OrderStatus { get; set; }
        public decimal Discount { get; set; } = decimal.Zero;
        public decimal TotalValue { get; set; }

        public OrderDTO(long id, DateTime orderDate, OrderStatus orderStatus, decimal discount, decimal totalValue) {
            Id = id;
            OrderDate = orderDate;
            OrderStatus = orderStatus;
            Discount = discount;
            TotalValue = totalValue;
        }
    }
}

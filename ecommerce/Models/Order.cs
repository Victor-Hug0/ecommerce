namespace ecommerce.Models {
    public class Order {
        private int Id { get; set; }
        private int ClientId { get; set; }
        private DateTime OrderDate { get; set; }
        private int StatusId { get; set; }
        private int ShippingAdressId { get; set; }
        private decimal discount { get; set; }
        private decimal TotalValue { get; set; }
        public virtual OrderStatus Status { get; set; }
        public virtual Client Client { get; set; }
        public virtual ShippingAddress ShippingAddress { get; set; }
        public virtual ICollection<OrderItem> Items { get; set; }

    }
}

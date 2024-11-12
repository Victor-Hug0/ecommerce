namespace ecommerce.Models {
    public class OrderItem {
        private int Id { get; set; }
        private int OrderId { get; set; }
        private int ProductId { get; set; }
        private int Quantity { get; set; }
        private decimal UnitPrice { get; set; }
        private decimal SubTotalPrice => Quantity * UnitPrice;
        public virtual Order Order { get; set; }
        public virtual Product Product { get; set; }
    }
}

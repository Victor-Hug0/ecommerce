namespace ecommerce.Models {
    public class Product {
        private int Id { get; set; }
        private string Name { get; set; }
        private string Description { get; set; }
        private decimal Price { get; set; }
        private int StockQuantity { get; set; }
        private decimal StockPrice { get; set; }
        private int CategotyId { get; set; }
        public virtual Category Category { get; set; }
        public virtual ICollection<Review> Reviews { get; set; }
        public int VendorId { get; set; }
        public virtual Vendor Vendor { get; set; }
    }
}
    
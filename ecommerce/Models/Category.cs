namespace ecommerce.Models {
    public class Category {
        private int Id { get; set; }
        private string Name { get; set; }
        private string Description { get; set; }
        public virtual ICollection<Product> Products { get; set; }
    }
}

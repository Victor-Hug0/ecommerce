using Newtonsoft.Json;
using Newtonsoft.Json.Converters;
using System.ComponentModel.DataAnnotations.Schema;

namespace ecommerce.Models {
    [Table("products")]
    public class Product {
        public long Id { get; set; }
        public string Name { get; set; }
        public string Description { get; set; }
        public decimal Price { get; set; }
        public int StockQuantity { get; set; }
        public decimal StockPrice { get; set; }
        public Category Category { get; set; }
        public virtual ICollection<Review> Reviews { get; set; }
        public long VendorId { get; set; }
        public virtual Vendor Vendor { get; set; }

        public Product(string name, string description, decimal price, int stockQuantity, decimal stockPrice, Category category, long vendorId) {
            Name = name;
            Description = description;
            Price = price;
            StockQuantity = stockQuantity;
            StockPrice = stockPrice;
            Category = category;
            VendorId = vendorId;
        }
    }
}
    
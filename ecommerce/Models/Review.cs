using System.ComponentModel.DataAnnotations.Schema;

namespace ecommerce.Models {
    [Table("reviews")]
    public class Review {
        public long Id { get; set; }
        public long ProductId { get; set; }
        public long ClientId { get; set; }
        public int Rating { get; set; }
        public string Comment { get; set; }
        public DateTime ReviewDate { get; set; }
        public virtual Product Product { get; set; }
        public virtual Client Client { get; set; }

        public Review(long productId, long clientId, int rating, string comment) {
            ProductId = productId;
            ClientId = clientId;
            Rating = rating;
            Comment = comment;
            ReviewDate = DateTime.UtcNow;
        }
    }
}

namespace ecommerce.Models {
    public class Review {
        private int Id { get; set; }
        private int ProductId { get; set; }
        private int ClientId { get; set; }
        private int Rating { get; set; }
        private DateTime ReviewDate { get; set; }
        public virtual Product Product { get; set; }
        public virtual Client Client { get; set; }

    }
}

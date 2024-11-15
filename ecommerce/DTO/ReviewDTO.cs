namespace ecommerce.DTO {
    public class ReviewDTO {
        public long Id { get; set; }
        public int Rating { get; set; }
        public string Comment { get; set; }
        public DateTime ReviewDate { get; set; }

        public ReviewDTO(long id, int rating, string comment, DateTime reviewDate) {
            Id = id;
            Rating = rating;
            Comment = comment;
            ReviewDate = reviewDate;
        }
    }
}

namespace ecommerce.Models {
    public class ShippingAddress {
        private int Id { get; set; }
        private int ClientId { get; set; }
        private string street { get; set; }
        private string city { get; set; }
        private string state { get; set; }
        private string zipcode { get; set; }
        private string number { get; set; }
        private string complement { get; set; }
        public virtual Client Client { get; set; }
    }
}

namespace ecommerce.Models {
    public class Client {
        public long Id { get; set; }
        public string Name { get; set; }
        public string Email { get; set; }
        public string PhoneNumber { get; set; }
        public DateTime RegistrationDate { get; set; } = DateTime.UtcNow;
        public virtual ICollection<Order> Orders {  get; set; }
        public virtual ICollection<Review> Reviews { get; set; }
        public Client(string name, string email, string phoneNumber) {
            Name = name;
            Email = email;
            PhoneNumber = phoneNumber;
            RegistrationDate = DateTime.UtcNow;
        }
    }
}

using System.ComponentModel.DataAnnotations.Schema;

namespace ecommerce.Models {
    [Table("shippingaddresses")]
    public class ShippingAddress {
        public long Id { get; set; }
        public string Street { get; set; }
        public string Number { get; set; }
        public string City { get; set; }
        public string State { get; set; }
        public string ZipCode { get; set; }
        public string Complement { get; set; }

        public ShippingAddress(string street, string number, string city, string state, string zipCode, string complement) {
            Street = street;
            Number = number;
            City = city;
            State = state;
            ZipCode = zipCode;
            Complement = complement;
        }
    }
}

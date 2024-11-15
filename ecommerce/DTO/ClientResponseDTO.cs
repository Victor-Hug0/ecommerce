using ecommerce.Models;

namespace ecommerce.DTO {
    public class ClientResponseDTO {
        public long Id {  get; set; }
        public string Name { get; set; }
        public string Email { get; set; }
        public List<OrderDTO> Orders { get; set; }
        public List<ReviewDTO> Reviews { get; set; }

        public ClientResponseDTO(long id, string name, string email, List<OrderDTO> orders, List<ReviewDTO> reviews) {
            Id = id;
            Name = name;
            Email = email;
            Orders = orders;
            Reviews = reviews;
        }


    }
}

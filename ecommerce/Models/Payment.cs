using System.ComponentModel.DataAnnotations.Schema;

namespace ecommerce.Models {
    [Table("payments")]
    public class Payment {
        public long Id { get; set; }
        public long OrderId { get; set; }
        public string Method { get; set; }
        [Column("Status")]
        public PaymentStatus PaymentStatus { get; set; }
        public DateTime PaymentDate { get; set; }
        public decimal Tax { get; set; }
        public decimal TotalAmountPaid { get; set; }
        public virtual Order Order { get; set; }

        public Payment(long orderId, string method, PaymentStatus paymentStatus, decimal tax, decimal totalAmountPaid) {
            OrderId = orderId;
            Method = method;
            PaymentStatus = paymentStatus;
            PaymentDate = DateTime.UtcNow;
            Tax = tax;
            TotalAmountPaid = totalAmountPaid;
        }
    }
}

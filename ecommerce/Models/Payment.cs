namespace ecommerce.Models {
    public class Payment {
        private int id { get; set; }
        private int OrderId { get; set; }
        private string Method { get; set; }
        private string Status {  get; set; }
        private DateTime PaymentDate { get; set; }
        private decimal Tax { get; set; }
        private decimal TotalAmountPaid { get; set; }
        public virtual Order Order { get; set; }
    }
}

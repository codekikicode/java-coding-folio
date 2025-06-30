public class PaymentTransaction {
    private int transactionNumber;
    private int customerNumber;
    private double amountPaid;
    private double earlyDiscount;

    public PaymentTransaction(int transactionNumber, int customerNumber,
                              double amountPaid, double earlyDiscount) {
        this.transactionNumber = transactionNumber;
        this.customerNumber = customerNumber;
        this.amountPaid = amountPaid;
        this.earlyDiscount = earlyDiscount;
    }

    public int getCustomerNumber() {
        return customerNumber;
    }

    public double getPaymentAmount() {
        return amountPaid - earlyDiscount;
    }
}
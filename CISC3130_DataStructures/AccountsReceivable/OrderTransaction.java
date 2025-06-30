public class OrderTransaction {
    private int transactionNumber;
    private int customerNumber;
    private String itemName;
    private int quantity;
    private double price;
    private double extraDiscount;

    public OrderTransaction(int transactionNumber, int customerNumber, String itemName, 
int quantity, double price, double extraDiscount) {
        this.transactionNumber = transactionNumber;
        this.customerNumber = customerNumber;
        this.itemName = itemName;
        this.quantity = quantity;
        this.price = price;
        this.extraDiscount = extraDiscount;
    }

    public int getCustomerNumber() {
        return customerNumber;
    }

    public double getTotalCost() {
        return (price * quantity) - extraDiscount;
    }
}
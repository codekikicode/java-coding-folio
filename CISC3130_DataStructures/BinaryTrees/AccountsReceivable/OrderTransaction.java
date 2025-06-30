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

    public int getTransactionNumber() { return transactionNumber; }
    public int getCustomerNumber() { return customerNumber; }
    public String getItemName() { return itemName; }
    public int getQuantity() { return quantity; }
    public double getPrice() { return price; }
    public double getExtraDiscount() { return extraDiscount; }
    public double getDiscountedAmount() {
        return getTotalCost() * (1 - (extraDiscount / 100));
    }

    public double getTotalCost() {
        return (quantity * price) * (1 - extraDiscount / 100);
}
}
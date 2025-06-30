public class Customer {
    int customerNumber;
    String customerName;
    double balanceDue;
    int standardDiscount;

    public Customer(int customerNumber, String customerName, double balanceDue, int standardDiscount) {
        this.customerNumber = customerNumber;
        this.customerName = customerName;
        this.balanceDue = balanceDue;
        this.standardDiscount = standardDiscount;
    }


    public String toString() {
        return customerNumber + " " + customerName + " $" + String.format("%.2f", balanceDue) + " Discount: " + standardDiscount + "%";
    }
}

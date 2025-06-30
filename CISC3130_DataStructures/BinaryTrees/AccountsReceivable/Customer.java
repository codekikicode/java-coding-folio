public class Customer {
    private int customerNumber;
    private String customerName;
    private double balanceDue;
    private double standardDiscount;
    private double originalBalance;

    public Customer(int customerNumber, String customerName, double balanceDue, double standardDiscount) {
        this.customerNumber = customerNumber;
        this.customerName = customerName;
        this.balanceDue = balanceDue;
        this.standardDiscount = standardDiscount;
        this.originalBalance = balanceDue;
    }

    public int getCustomerNumber() {
        return customerNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public double getBalanceDue() {
        return balanceDue;
    }

    public double getStandardDiscount() {
        return standardDiscount;
    }

    public double getOriginalBalance() {
        return originalBalance;
    }

    public void updateBalance(double amount) {
        this.balanceDue += amount;
    }

    public String toString() {
        return customerNumber + " " + customerName + " " + balanceDue + " " + standardDiscount;
    }
}
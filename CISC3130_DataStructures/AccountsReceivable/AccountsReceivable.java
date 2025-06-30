import java.io.*;
import java.util.*;

public class AccountsReceivable {
    public static void main(String[] args) {
        List<Customer> customers = new ArrayList<>();
        List<OrderTransaction> orders = new ArrayList<>();
        List<PaymentTransaction> payments = new ArrayList<>();

        String customerFile = "trans_data/customers.txt";
        String transactionFile = "trans_data/transactions.txt";
        String outputFile = "trans_data/processed_data.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(customerFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;

                String[] parts = line.split("\\s+");
                if (parts.length < 4) {
                    System.err.println("Skip malformed customer line: " + line);
                    continue;
                }
                int customerNumber = Integer.parseInt(parts[0]);
                String customerName = parts[1];
                double balanceDue = Double.parseDouble(parts[2]);
                double standardDiscount = Double.parseDouble(parts[3]);

                customers.add(new Customer(customerNumber, customerName, balanceDue, standardDiscount));
            }
            System.out.println("customers.txt loaded successfully!");
        } catch (IOException e) {
            System.err.println("ERROR: Unable to read " + customerFile + "!");
            e.printStackTrace();
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(transactionFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;

                String[] parts = line.split("\\s+");
                char type = parts[0].charAt(0);

                if (type == 'O') {
                    if (parts.length < 7) {
                        System.err.println("Skip malformed order line: " + line);
                        continue;
                    }
                    int customerNumber = Integer.parseInt(parts[1]);
                    int transactionNumber = Integer.parseInt(parts[2]);
                    String itemName = parts[3];
                    int quantity = Integer.parseInt(parts[4]);
                    double price = Double.parseDouble(parts[5]);
                    double extraDiscount = Double.parseDouble(parts[6]);

                    orders.add(new OrderTransaction(transactionNumber, customerNumber, itemName, quantity, price, extraDiscount));

                } else if (type == 'P') {
                    if (parts.length < 5) {
                        System.err.println("Skip malformed payment line: " + line);
                        continue;
                    }
                    int customerNumber = Integer.parseInt(parts[1]);
                    int transactionNumber = Integer.parseInt(parts[2]);
                    double amountPaid = Double.parseDouble(parts[3]);
                    double earlyDiscount = Double.parseDouble(parts[4]);

                    payments.add(new PaymentTransaction(transactionNumber, customerNumber, amountPaid, earlyDiscount));
                } else {
                    System.err.println("Skip unknown transaction type: " + line);
                }
            }
            System.out.println("transactions.txt loaded successfully!");
        } catch (IOException e) {
            System.err.println("ERROR: Unable to read " + transactionFile + "!");
            e.printStackTrace();
            return;
        }

        for (Customer c : customers) {
            System.out.println("Processing transactions for: " + c.getCustomerName() + " (ID: " + c.getCustomerNumber() + ")");
            double initialBalance = c.getBalanceDue();

            for (OrderTransaction order : orders) {
                if (c.getCustomerNumber() == order.getCustomerNumber()) {
                    double totalCost = order.getTotalCost();
                    c.updateBalance(totalCost);
                    System.out.printf("  Order Submission: +%.2f (Item: %s)\n", totalCost, order.getItemName());
                }
            }

            for (PaymentTransaction payment : payments) {
                if (c.getCustomerNumber() == payment.getCustomerNumber()) {
                    double paymentAmount = payment.getPaymentAmount();
                    c.updateBalance(-paymentAmount);
                    System.out.printf("  Payment Applied: -%.2f\n", paymentAmount);
                }
            }
            System.out.printf("  Final Balance for %s: $%.2f (original amt: $%.2f)\n\n", c.getCustomerName(), c.getBalanceDue(), initialBalance);
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile))) {
            for (Customer c : customers) {
                String output = String.format("%d %s %.2f %.2f", c.getCustomerNumber(), c.getCustomerName(), c.getBalanceDue(), c.getStandardDiscount());
                bw.write(output);
                bw.newLine();
            }
            System.out.println("Processed data has been written to " + outputFile + "!");
        } catch (IOException e) {
            System.err.println("ERROR writing to " + outputFile + "!");
            e.printStackTrace();
        }
    }
}
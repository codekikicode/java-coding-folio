public class InventoryManager {
    WoodNode oakTree;
    WoodNode cherryTree;
    double nextPromo;

    public InventoryManager() {
        oakTree = null;
        cherryTree = null;
        nextPromo = 0.0;
    }

    public void addReceipt(char woodType, int quantity, double pricePerLog) {
        WoodNode newNode = new WoodNode(quantity, pricePerLog);
        if (woodType == 'O') {
            if (oakTree == null) {
                oakTree = newNode;
            } else {
                WoodNode current = oakTree;
                while (current.next != null) {
                    current = current.next;
                }
                current.next = newNode;
            }

            System.out.println(quantity + " logs of Oak wood received at $" + pricePerLog + " each.");

        } else if (woodType == 'C') {
            if (cherryTree == null) {
                cherryTree = newNode;
            } else {
                WoodNode current = cherryTree;
                while (current.next != null) {
                    current = current.next;
                }
                current.next = newNode;
            }
            System.out.println(quantity + " logs of Cherry Maple wood received at $" + pricePerLog + " each.");
        }
    }

    public void processSale(char woodType, int requestedQuantity) {
        WoodNode current = (woodType == 'O') ? oakTree : cherryTree;
        double markup = 0.30;
        double totalSale = 0.0;

        System.out.println();
        System.out.println(requestedQuantity + " logs of " +
                (woodType == 'O' ? "Oak" : "Cherry Maple") + " wood requested.");

        while (current != null && requestedQuantity > 0) {
            if (current.quantity == 0) {
                current = current.next;
                continue;
            }

            int sold = Math.min(requestedQuantity, current.quantity);
            double salePriceEach = current.pricePerLog * (1 + markup);
            double salePrice = sold * salePriceEach;

            System.out.printf("%d at $%.2f each\n", sold, salePriceEach);
            System.out.printf("Sales: $%.2f\n", salePrice);

            totalSale += salePrice;
            requestedQuantity -= sold;
            current.quantity -= sold;
        }

        if (requestedQuantity > 0) {
            System.out.println("Remainder of " + requestedQuantity + " logs of " +
                    (woodType == 'O' ? "Oak" : "Cherry Maple") + " not available.");
        }

        if (nextPromo > 0) {
            double discount = totalSale * nextPromo;
            totalSale -= discount;
            System.out.printf("Promotion applied: %.0f%% off\n", nextPromo * 100);
            System.out.printf("Discount: $%.2f\n", discount);
            nextPromo = 0.0;
        }

        System.out.printf("Total Sale: $%.2f\n", totalSale);
        System.out.println();
    }
    public class InventoryManagerTest {
        public static void main(String[] args) {
            InventoryManager manager = new InventoryManager();

            manager.addReceipt('O', 150, 3.00);
            manager.addReceipt('C', 130, 5.00);
            manager.addReceipt('O', 50, 6.00);

            manager.processSale('O', 145);
        }
    }
    public void addPromotion(double discountRate) {
        nextPromo = discountRate / 100.0;
        System.out.println("Promotion card read. Next customer gets " + discountRate + "% off.");
    }
    public void printRemainingInventory() {
        System.out.println("\n===== INVENTORY REPORT =====");

        System.out.println("\nRemaining Oak wood:");
        printList(oakTree);

        System.out.println("\nRemaining Cherry Maple wood:");
        printList(cherryTree);
    }

    private void printList(WoodNode head) {
        if (head == null) {
            System.out.println("None");
            return;
        }

        WoodNode current = head;
        while (current != null) {
            if (current.quantity > 0) {
                System.out.printf("%d logs at original price $%.2f each\n", current.quantity, current.pricePerLog);
            }
            current = current.next;
        }
    }
}
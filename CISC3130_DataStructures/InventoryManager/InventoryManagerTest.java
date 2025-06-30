public class InventoryManagerTest {
    public static void main(String[] args) {
        InventoryManager manager = new InventoryManager();

        manager.addReceipt('O', 150, 3.00);
        manager.addReceipt('C', 130, 5.00);
        manager.processSale('O', 145);
        manager.addReceipt('O', 50, 6.00);
        manager.processSale('C', 75);
        manager.processSale('O', 180);
        manager.addReceipt('C', 50, 7.00);
        manager.addReceipt('C', 30, 8.00);
        manager.addReceipt('O', 40, 9.00);
        manager.addPromotion(30);
        manager.processSale('C', 50);
        manager.processSale('O', 30);
        manager.addReceipt('C', 50, 10.00);
        manager.addReceipt('O', 265, 11.00);
        manager.processSale('O', 60);
        manager.addPromotion(50);
        manager.processSale('O', 100);
        manager.processSale('C', 70);
        manager.processSale('O', 175);
        manager.addReceipt('O', 40, 13.00);
        manager.addReceipt('O', 75, 15.00);
        manager.addReceipt('O', 50, 16.00);
        manager.addReceipt('C', 50, 19.00);
        manager.addReceipt('O', 80, 20.00);

        manager.printRemainingInventory();
    }
}
package adventOfCode.day2;

public class InventoryManagementSystemTest {
    public static void main(String[] args) {

        InventoryManagementSystem theThing = new InventoryManagementSystem("input", "2");
        System.out.println(theThing.calculateChecksum());
        System.out.println(theThing.commonLetters());
    }
}

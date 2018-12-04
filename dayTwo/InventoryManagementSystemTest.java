package adventOfCode.dayTwo;

import java.nio.file.Path;
import java.nio.file.Paths;

public class InventoryManagementSystemTest {
    public static void main(String[] args) {

        InventoryManagementSystem theThing = new InventoryManagementSystem("input", "Two");
        System.out.println(theThing.calculateChecksum());
    }
}

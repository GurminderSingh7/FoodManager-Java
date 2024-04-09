package Test;

// Import JUnit assertion methods
import org.junit.Test;
import static org.junit.Assert.assertEquals;

// Import the InventoryItem class from the Controller package
import Controller.InventoryItem;

// Import Date class from java.util
import java.util.Date;

/**
 * This class provides unit tests for the InventoryItem class.
 * It tests the constructors and getters/setters of InventoryItem.
 * Author: Meet Maheta
 */
public class InventoryItemTest {

    /**
     * Test case to verify the constructors and getters of InventoryItem.
     */
    @Test
    public void testInventoryItemConstructorAndGetters() {
        // Create an inventory item
        int itemId = 1;
        String name = "Test Item";
        int quantity = 10;
        Date expirationDate = new Date();
        double price = 25.99;
        String status = "Available";
        String action = "None";
        InventoryItem item = new InventoryItem(itemId, name, quantity, expirationDate, price, status, action);

        // Verify item attributes
        assertEquals(itemId, item.getItemId());
        assertEquals(name, item.getName());
        assertEquals(quantity, item.getQuantity());
        assertEquals(expirationDate, item.getExpirationDate());
        assertEquals(price, item.getPrice(), 0.001); // delta used for double comparison
        assertEquals(status, item.getStatus());
        assertEquals(action, item.getAction());
    }

    /**
     * Test case to verify the setters of InventoryItem.
     */
    @Test
    public void testInventoryItemSetters() {
        // Create an inventory item
        InventoryItem item = new InventoryItem(1, "Test Item", 10, new Date(), 25.99, "Available", "None");

        // Modify attributes
        item.setItemId(2);
        item.setName("Updated Item");
        item.setQuantity(5);
        Date newExpirationDate = new Date();
        item.setExpirationDate(newExpirationDate);
        item.setPrice(19.99);
        item.setStatus("Sold");
        item.setAction("Discounted");

        // Verify modifications
        assertEquals(2, item.getItemId());
        assertEquals("Updated Item", item.getName());
        assertEquals(5, item.getQuantity());
        assertEquals(newExpirationDate, item.getExpirationDate());
        assertEquals(19.99, item.getPrice(), 0.001); // delta used for double comparison
        assertEquals("Sold", item.getStatus());
        assertEquals("Discounted", item.getAction());
    }
}

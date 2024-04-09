package Controller;

import java.util.Date;

/**
 * Represents an item in the inventory.
 * 
 * This class encapsulates attributes and methods related to inventory items.
 * 
 * @author Meet Maheta
 */
public class InventoryItem {
    private int itemId;
    private String name;
    private int quantity;
    private Date expirationDate;
    private double price;
    private String status;
    private String action;

    /**
     * Constructs an InventoryItem object with specified attributes.
     * 
     * @param itemId         The ID of the item.
     * @param name           The name of the item.
     * @param quantity       The quantity of the item.
     * @param expirationDate The expiration date of the item.
     * @param price          The price of the item.
     * @param status         The status of the item.
     * @param action         The action associated with the item.
     */
    public InventoryItem(int itemId, String name, int quantity, Date expirationDate, double price, String status, String action) {
        this.itemId = itemId;
        this.name = name;
        this.quantity = quantity;
        this.expirationDate = expirationDate;
        this.price = price;
        this.status = status;
        this.action = action;
    }

    // Getters and setters for all attributes

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}

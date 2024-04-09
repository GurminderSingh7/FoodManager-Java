package DAO;

import Controller.InventoryItem;
import Connection.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Data Access Object for Inventory Items.
 * 
 * This class provides methods to interact with the database regarding inventory items.
 * 
 * @author Meet Maheta
 * @author Parth Patel
 * @author Aditya Hirpara
 * @author Gurminder Singh Badwal
 * 
 */
public class InventoryItemDAO {

    // Method to add a new inventory item to the database with claimable flag
    public boolean addInventoryItem(InventoryItem item) {
        String sql = "INSERT INTO inventory_items (name, quantity, expiration_date, price, status, action) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, item.getName());
            pstmt.setInt(2, item.getQuantity());
            pstmt.setDate(3, new java.sql.Date(item.getExpirationDate().getTime()));
            pstmt.setDouble(4, item.getPrice());
            pstmt.setString(5, item.getStatus());
            pstmt.setString(6, item.getAction());

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to retrieve items by email
    public List<InventoryItem> getItemsByEmail(String email) {
        List<InventoryItem> items = new ArrayList<>();
        String sql = "SELECT * FROM inventory_items WHERE email = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, email);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    InventoryItem item = new InventoryItem(
                            rs.getInt("item_id"),
                            rs.getString("name"),
                            rs.getInt("quantity"),
                            rs.getDate("expiration_date"),
                            rs.getDouble("price"),
                            rs.getString("status"),
                            rs.getString("action"));
                    items.add(item);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

    // Method to retrieve donation items for a charity
    public List<InventoryItem> getDonationItemsForCharity() {
        List<InventoryItem> donationItems = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 7); // Add 7 days to the current date

        // Modify the SQL query to select only donation items
        String sql = "SELECT * FROM inventory_items WHERE expiration_date < ? AND action = 'Donation'";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDate(1, new java.sql.Date(calendar.getTime().getTime()));
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    InventoryItem item = new InventoryItem(
                            rs.getInt("item_id"),
                            rs.getString("name"),
                            rs.getInt("quantity"),
                            rs.getDate("expiration_date"),
                            rs.getDouble("price"),
                            rs.getString("status"),
                            rs.getString("action"));
                    donationItems.add(item);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return donationItems;
    }

    // Method to retrieve discounted sale items for consumers
    public List<InventoryItem> getDiscountedSaleItemsForConsumer() {
        List<InventoryItem> discountedSaleItems = new ArrayList<>();
        String sql = "SELECT * FROM inventory_items WHERE action = 'Discounted Sale'";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                InventoryItem item = new InventoryItem(
                        rs.getInt("item_id"),
                        rs.getString("name"),
                        rs.getInt("quantity"),
                        rs.getDate("expiration_date"),
                        rs.getDouble("price"),
                        rs.getString("status"),
                        rs.getString("action"));
                discountedSaleItems.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return discountedSaleItems;
    }

    // Method to add a new donation item to the database
    public boolean addDonationItem(InventoryItem item) {
        String sql = "INSERT INTO inventory_items (name, quantity, expiration_date, price, status, action, claimable) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, item.getName());
            pstmt.setInt(2, item.getQuantity());
            pstmt.setDate(3, new java.sql.Date(item.getExpirationDate().getTime()));
            pstmt.setDouble(4, item.getPrice());
            pstmt.setString(5, item.getStatus());
            pstmt.setString(6, "Donation");
            pstmt.setBoolean(7, true); // Donation items are claimable

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to update an existing inventory item in the database
    public boolean updateInventoryItem(InventoryItem item) {
        String sql = "UPDATE inventory_items SET name = ?, quantity = ?, expiration_date = ?, price = ?, status = ?, action = ? WHERE item_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, item.getName());
            pstmt.setInt(2, item.getQuantity());
            pstmt.setDate(3, new java.sql.Date(item.getExpirationDate().getTime()));
            pstmt.setDouble(4, item.getPrice());
            pstmt.setString(5, item.getStatus());
            pstmt.setString(6, item.getAction());
            pstmt.setInt(8, item.getItemId());

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to add a new discounted sale item to the database
    public boolean addDiscountedSaleItem(InventoryItem item) {
        String sql = "INSERT INTO inventory_items (name, quantity, expiration_date, price, status, action, claimable) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, item.getName());
            pstmt.setInt(2, item.getQuantity());
            pstmt.setDate(3, new java.sql.Date(item.getExpirationDate().getTime()));
            pstmt.setDouble(4, item.getPrice());
            pstmt.setString(5, item.getStatus());
            pstmt.setString(6, "Discounted Sale");
            pstmt.setBoolean(7, false); // Discounted sale items are not claimable

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to retrieve all inventory items from the database
    public List<InventoryItem> getAllInventoryItems() {
        List<InventoryItem> itemList = new ArrayList<>();
        String sql = "SELECT * FROM inventory_items";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                InventoryItem item = new InventoryItem(
                        rs.getInt("item_id"),
                        rs.getString("name"),
                        rs.getInt("quantity"),
                        rs.getDate("expiration_date"),
                        rs.getDouble("price"),
                        rs.getString("status"),
                        rs.getString("action"));

                itemList.add(item);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return itemList;
    }

    // Method to retrieve a single inventory item by its ID
    public InventoryItem getInventoryItemById(int itemId) {
        String sql = "SELECT * FROM inventory_items WHERE item_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, itemId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new InventoryItem(
                            rs.getInt("item_id"),
                            rs.getString("name"),
                            rs.getInt("quantity"),
                            rs.getDate("expiration_date"),
                            rs.getDouble("price"),
                            rs.getString("status"),
                            rs.getString("action"));
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Method to claim a donation item by a charitable organization
    public boolean claimDonationItem(int itemId) {
        String sql = "UPDATE inventory_items SET claimable = false WHERE item_id = ? AND action = 'Donation'";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, itemId);

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to retrieve all donation items from the database
    public List<InventoryItem> getDonationItems() {
        List<InventoryItem> donationItems = new ArrayList<>();
        String sql = "SELECT * FROM inventory_items WHERE action = 'Donation'";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                InventoryItem item = new InventoryItem(
                        rs.getInt("item_id"),
                        rs.getString("name"),
                        rs.getInt("quantity"),
                        rs.getDate("expiration_date"),
                        rs.getDouble("price"),
                        rs.getString("status"),
                        rs.getString("action"));

                donationItems.add(item);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return donationItems;
    }

    // Method to filter regular inventory items
    public List<InventoryItem> getRegularInventoryItems(List<InventoryItem> allInventoryItems) {
        List<InventoryItem> regularInventoryItems = new ArrayList<>();
        for (InventoryItem item : allInventoryItems) {
            if (!isSurplusFoodItem(item)) {
                regularInventoryItems.add(item);
            }
        }
        return regularInventoryItems;
    }

    // Helper method to check if an item is a surplus food item
    private boolean isSurplusFoodItem(InventoryItem item) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 7); // Add 7 days to the current date
        return item.getExpirationDate().before(calendar.getTime());
    }

    // Method to retrieve surplus food items from the database
    public List<InventoryItem> getSurplusFoodItems() {
        List<InventoryItem> surplusItems = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 7); // Add 7 days to the current date

        String sql = "SELECT * FROM inventory_items WHERE expiration_date < ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDate(1, new java.sql.Date(calendar.getTime().getTime()));
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    InventoryItem item = new InventoryItem(
                            rs.getInt("item_id"),
                            rs.getString("name"),
                            rs.getInt("quantity"),
                            rs.getDate("expiration_date"),
                            rs.getDouble("price"),
                            rs.getString("status"),
                            rs.getString("action"));

                    surplusItems.add(item);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return surplusItems;
    }
    
    // Method to delete an inventory item from the database
    public boolean deleteInventoryItem(int itemId) {
        String sql = "DELETE FROM inventory_items WHERE item_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, itemId);

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}

package DAO;

import Controller.InventoryItem;
import Connection.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Data Access Object for Surplus Items.
 * 
 * This class provides methods to interact with the database regarding surplus items.
 * 
 * @author Aditya Hirpara
 */
public class SurplusItemDAO {

    // Method to retrieve surplus food items for a charity
    public List<InventoryItem> getSurplusFoodItemsForCharity() {
        List<InventoryItem> surplusItems = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 7); // Add 7 days to the current date

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

                    surplusItems.add(item);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return surplusItems;
    }
}

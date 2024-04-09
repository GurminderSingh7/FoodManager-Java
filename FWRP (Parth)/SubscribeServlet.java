package Servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import Connection.DatabaseConnection;

/**
 * Servlet implementation class for subscribing users to notifications.
 * 
 * This servlet handles the subscription form submission and inserts the subscription details into the database.
 * 
 * Author: Parth Patel
 */
@WebServlet("/SubscribeServlet")
public class SubscribeServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve subscription form parameters
        String email = request.getParameter("email");
        String location = request.getParameter("location");
        String phoneNumber = request.getParameter("phoneNumber");

        try {
            // Get a connection to the database
            Connection conn = DatabaseConnection.getConnection();

            // Prepare SQL statement for insertion
            String sql = "INSERT INTO subscriptions (email, location, phone_number) VALUES (?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            // Set values for parameters
            pstmt.setString(1, email);
            pstmt.setString(2, location);
            pstmt.setString(3, phoneNumber);

            // Execute the SQL statement
            pstmt.executeUpdate();

            // Close the PreparedStatement and Connection
            pstmt.close();
            conn.close();
            
            // Redirect to subscription success page
            response.sendRedirect("subscription_success.html");
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle database errors appropriately
        }
    }
}

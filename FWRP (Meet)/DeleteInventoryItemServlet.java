package Servlet;

import DAO.InventoryItemDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class for deleting an inventory item.
 * 
 * This servlet processes the request to delete an inventory item from the system.
 * 
 * @author Meet Maheta
 */
@WebServlet("/DeleteInventoryItemServlet")
public class DeleteInventoryItemServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int itemId = Integer.parseInt(request.getParameter("id"));

            // Instantiate InventoryItemDAO
            InventoryItemDAO dao = new InventoryItemDAO();

            // Call the deleteInventoryItem method
            boolean success = dao.deleteInventoryItem(itemId);

            if (success) {
                response.sendRedirect("InventoryListServlet");
            } else {
                request.setAttribute("errorMessage", "Unable to delete item. Please try again.");
                request.getRequestDispatcher("/inventory-list.jsp").forward(request, response);
            }
        } catch (IOException | ServletException e) {
            log("Error processing delete inventory item request", e);
            e.printStackTrace(); // Add this line to print stack trace
            request.setAttribute("errorMessage", "An unexpected error occurred: " + e.getMessage());
            request.getRequestDispatcher("/inventory-list.jsp").forward(request, response);
        } catch (Exception e) {
            log("Unexpected error in delete inventory item servlet", e);
            e.printStackTrace(); // Add this line to print stack trace
            request.setAttribute("errorMessage", "An unexpected error occurred: " + e.getMessage());
            request.getRequestDispatcher("/inventory-list.jsp").forward(request, response);
        }
    }
}

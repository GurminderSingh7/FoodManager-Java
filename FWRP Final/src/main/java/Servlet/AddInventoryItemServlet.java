package Servlet;

import Controller.InventoryItem;
import DAO.InventoryItemDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Servlet implementation class for adding an inventory item.
 * 
 * This servlet processes the request to add a new inventory item to the system.
 * 
 * @author Meet Maheta
 */
@WebServlet("/AddInventoryItemServlet")
public class AddInventoryItemServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String name = request.getParameter("name");
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            double price = Double.parseDouble(request.getParameter("price"));
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date expirationDate = dateFormat.parse(request.getParameter("expirationDate"));
            String status = request.getParameter("status");
            String action = request.getParameter("action");
          
            // Construct InventoryItem with all attributes
            InventoryItem item = new InventoryItem(0, name, quantity, expirationDate, price, status, action);

            InventoryItemDAO dao = new InventoryItemDAO();
            boolean success = dao.addInventoryItem(item);

            if (success) {
                response.sendRedirect("InventoryListServlet?filter=" + status);
            } else {
                request.setAttribute("errorMessage", "Unable to add item. Please try again.");
                request.getRequestDispatcher("/inventory-list.jsp").forward(request, response);
            }
        } catch (IOException | ServletException e) {
            log("Error processing add inventory item request", e);
            e.printStackTrace();
            request.setAttribute("errorMessage", "An unexpected error occurred: " + e.getMessage());
            request.getRequestDispatcher("/inventory-list.jsp").forward(request, response);
        } catch (Exception e) {
            log("Unexpected error in add inventory item servlet", e);
            e.printStackTrace();
            request.setAttribute("errorMessage", "An unexpected error occurred: " + e.getMessage());
            request.getRequestDispatcher("/inventory-list.jsp").forward(request, response);
        }
    }
}

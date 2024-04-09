package Servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import Controller.InventoryItem;
import DAO.InventoryItemDAO;

/**
 * Servlet implementation class for managing charity inventory.
 * 
 * This servlet retrieves surplus items for charity from the database and forwards the request to the charity inventory page.
 * 
 * @author Aditya Hirpara
 */
@WebServlet("/CharityInventoryServlet")
public class CharityInventoryServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Instantiate InventoryItemDAO
        InventoryItemDAO dao = new InventoryItemDAO();
        
        // Get the list of surplus items excluding Discounted Sale items
        List<InventoryItem> surplusItems = dao.getDonationItemsForCharity();
        
        // Set surplus items as attribute in request
        request.setAttribute("surplusItems", surplusItems);
        
        // Forward the request to the JSP page
        request.getRequestDispatcher("/charity_inventory.jsp").forward(request, response);
    }
}

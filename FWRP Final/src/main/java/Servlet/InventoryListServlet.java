package Servlet;

// Import servlet-related classes
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// Import InventoryItem and InventoryItemDAO classes
import Controller.InventoryItem;
import DAO.InventoryItemDAO;

// Import List class from java.util
import java.util.List;

// Import IOException class from java.io
import java.io.IOException;

/**
 * Servlet implementation class InventoryListServlet
 * This servlet provides functionality to handle requests related to inventory listing.
 * 
 * @author Meet Maheta
 */
@WebServlet("/InventoryListServlet")
public class InventoryListServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * Create a method to allow for flexible creation of InventoryItemDAO.
     * @return An instance of InventoryItemDAO.
     */
    protected InventoryItemDAO createInventoryItemDAO() {
        return new InventoryItemDAO();
    }

    /**
     * Overrides the doGet method to handle GET requests.
     * Retrieves inventory items based on the provided filter and forwards the request to inventory-list.jsp.
     * @param request  The HttpServletRequest object.
     * @param response The HttpServletResponse object.
     * @throws ServletException If the request cannot be handled due to a servlet error.
     * @throws IOException      If an I/O error occurs while processing the request.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        InventoryItemDAO dao = createInventoryItemDAO(); // Use the method to create DAO
        List<InventoryItem> allInventoryItems = dao.getAllInventoryItems();
        
        // Retrieve the 'filter' parameter from the request
        String filter = request.getParameter("filter");
        
        // Depending on the filter value, set appropriate attributes
        if ("regular".equals(filter)) {
            // Only get and set regular inventory items if filter is 'regular'
            List<InventoryItem> regularInventoryItems = dao.getRegularInventoryItems(allInventoryItems);
            request.setAttribute("regularInventoryItems", regularInventoryItems);
        } else if ("surplus".equals(filter)) {
            // Only get and set surplus items if filter is 'surplus'
            List<InventoryItem> surplusItems = dao.getSurplusFoodItems();
            request.setAttribute("surplusItems", surplusItems);
        } else {
            // Default behavior: get and set both regular and surplus items
            List<InventoryItem> regularInventoryItems = dao.getRegularInventoryItems(allInventoryItems);
            List<InventoryItem> surplusItems = dao.getSurplusFoodItems();
            request.setAttribute("regularInventoryItems", regularInventoryItems);
            request.setAttribute("surplusItems", surplusItems);
        }

        // Forward the request and response to the inventory-list.jsp page
        request.getRequestDispatcher("/inventory-list.jsp").forward(request, response);
    }
}

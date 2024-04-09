package Servlet;

import Controller.InventoryItem;
import DAO.InventoryItemDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Servlet implementation  for managing consumer inventory.
 * 
 * This servlet retrieves discounted sale items for consumers from the database and forwards the request to the discounted items page.
 * 
 * @author Parth Patel
 */
@WebServlet("/ConsumerInventoryServlet")
public class ConsumerInventoryServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        InventoryItemDAO dao = new InventoryItemDAO();
        List<InventoryItem> discountedItems = dao.getDiscountedSaleItemsForConsumer();

        request.setAttribute("discountedItems", discountedItems);

        request.getRequestDispatcher("/discounted-items.jsp").forward(request, response);
    }
}

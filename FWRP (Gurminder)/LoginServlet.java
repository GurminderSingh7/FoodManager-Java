package Servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import Controller.User;
import DAO.UserDAO;

/**
 * Servlet implementation class for handling user login.
 * 
 * This servlet processes user login requests, authenticates users, and redirects them to appropriate pages based on their user type.
 * 
 * @author Gurminder Singh Badwal
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = -1943245059483344056L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password"); // In a real application, hash this before comparing

        UserDAO userDAO = new UserDAO();
        
        try {
            User user = userDAO.authenticateUser(email, password);
            if (user != null) {
                // If user is found and password matches
                HttpSession session = request.getSession();
                session.setAttribute("user", user); // Store user object in session
                session.setAttribute("retailerId", user.getUserId());
                // Redirect to a secured page or the user's dashboard based on userType
                switch (user.getUserType()) {
                    case "retailer":
                        response.sendRedirect("add-inventory-form.html"); // Placeholder URL
                        break;
                    case "consumer":
                        response.sendRedirect("ConsumerInventoryServlet");
                        break;
                    case "charity":
                        response.sendRedirect("CharityInventoryServlet"); // Redirect to claimable items page
                        break;
                    default:
                        response.sendRedirect("index.html"); // Fallback or error page
                }
            } else {
                // If authentication fails, redirect back to the login page with an error message
                request.setAttribute("errorMessage", "Invalid email or password");
                request.getRequestDispatcher("login.html").forward(request, response);
            }
        } catch (Exception e) {
            // Handle exceptions
            throw new ServletException("Login failed.", e);
        }
    }
}

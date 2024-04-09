package Servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import Controller.User;
import DAO.UserDAO;

/**
 * Servlet implementation class for user registration.
 * 
 * This servlet handles the registration of new users.
 * 
 * @author Gurminder Singh Badwal
 */
@WebServlet("/RegistrationServlet")
public class RegistrationServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password"); 
        String userType = request.getParameter("userType");

        User user = new User(0, name, email, password, userType);
        
        UserDAO userDAO = new UserDAO();
        boolean registerSuccess;
        try {
            registerSuccess = userDAO.registerUser(user);
        } catch (Exception e) {
            e.printStackTrace(); 
            registerSuccess = false;
        }

        if (registerSuccess) {
            response.sendRedirect("login.html");
        } else {
            request.setAttribute("errorMessage", "Registration failed. Please try again.");
            request.getRequestDispatcher("registration.html").forward(request, response);
        }
    }
}

package Test;

// Import JUnit assertion methods and annotations
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

// Import User and UserDAO classes
import Controller.User;
import DAO.UserDAO;

/**
 * This class provides unit tests for the UserDAO class.
 * It tests the registration and authentication functionalities.
 * Author: Meet Maheta
 */
public class UserDAOTest {

    // Instance of UserDAO for testing
    private UserDAO userDAO;
    
    // Set up before each test method
    @Before
    public void setUp() {
        userDAO = new UserDAO();
    }

    // Clean up after each test method
    @After
    public void tearDown() {
        // Clean up any resources if needed
    }

    // Test case for registering a user
    @Test
    public void testRegisterUser() {
        // Register a user with valid data
        User user = new User(1, "abc", "abc@gmail.com", "123", "retailer");
        assertTrue("Failed to register user", userDAO.registerUser(user));
    }

    // Test case for authenticating a user
    @Test
    public void testAuthenticateUser() {
        // Register a user first
        User user = new User(2, "xyz", "xyz@gmail.com", "456", "consumer");
        assertTrue("Failed to register user", userDAO.registerUser(user));
        
        // Authenticate the registered user
        User authenticatedUser = userDAO.authenticateUser("xyz@gmail.com", "456");
        
        // Assert that the user is authenticated
        assertNotNull("Failed to authenticate user", authenticatedUser);
        assertEquals("xyz", authenticatedUser.getName());
        assertEquals("xyz@gmail.com", authenticatedUser.getEmail());
        assertEquals("consumer", authenticatedUser.getUserType());
    }
}

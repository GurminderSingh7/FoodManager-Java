package Test;

// Import JUnit assertion methods
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

// Import necessary SQL classes
import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Test;

// Import the DatabaseConnection class from the Connection package
import Connection.DatabaseConnection;

/**
 * This class provides unit tests for the DatabaseConnection class.
 * It tests the functionality of establishing a database connection.
 * Author: Meet Maheta
 */
public class DatabaseConnectionTest {

    /**
     * Test case to verify the successful establishment of a database connection.
     * It checks if a connection is obtained and not null.
     */
    @Test
    public void testConnection() {
        try {
            // Attempt to establish a connection
            Connection connection = DatabaseConnection.getConnection();

            // Assert that the connection is not null
            assertNotNull("Connection should not be null", connection);

            // Close the connection after testing
            connection.close();
        } catch (SQLException e) {
            // If an SQL exception occurs, print the stack trace and fail the test
            e.printStackTrace();
            fail("Failed to establish connection: " + e.getMessage());
        }
    }
}

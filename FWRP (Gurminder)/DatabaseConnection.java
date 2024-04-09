package Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Provides a database connection utility for accessing the FWRP database.
 * This class encapsulates methods for establishing a connection with the database.
 * 
 * @author Gurminder Singh Badwal 
 * 
 */
public class DatabaseConnection {
    /** The URL of the database. */
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/FWRP";
    
    /** The username for accessing the database. */
    private static final String DATABASE_USER = "mmb0702";
    
    /** The password for accessing the database. */
    private static final String DATABASE_PASSWORD = "System #1234";

    static {
        try {
            // This will load the MySQL driver, each DB has its own driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new ExceptionInInitializerError(e);
        }
    }

    /**
     * Gets a connection to the database.
     * 
     * @return A connection to the database.
     * @throws SQLException If a database access error occurs.
     */
    public static Connection getConnection() throws SQLException {
        // Setup the connection with the DB
        return DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
    }
}

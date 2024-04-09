package Controller;

/**
 * Represents a user of the system.
 * 
 * This class encapsulates attributes and methods related to system users.
 *
 * @author Parth Patel
 */
public class User {
    private int userId;
    private String name;
    private String email;
    private String password;
    private String userType;

    // Default constructor
    public User() {
    }

    /**
     * Constructs a User object with specified attributes.
     * 
     * @param userId   The ID of the user.
     * @param name     The name of the user.
     * @param email    The email of the user.
     * @param password The password of the user.
     * @param userType The type of the user.
     */
    public User(int userId, String name, String email, String password, String userType) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.userType = userType;
    }

    // Getters

    public int getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getUserType() {
        return userType;
    }

    // Setters

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    // Override the toString() method for debugging purposes

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", userType='" + userType + '\'' +
                '}';
    }
}

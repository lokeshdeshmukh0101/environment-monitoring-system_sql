package model;

public class User {

    private int userId;
    private String username;
    private String role;

    // Default constructor
    public User() {}

    // Constructor for insert
    public User(int userId, String username, String role) {
        this.userId = userId;
        this.username = username;
        this.role = role;
    }

    // GETTERS
    public int getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getRole() {
        return role;
    }

    // SETTERS
    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
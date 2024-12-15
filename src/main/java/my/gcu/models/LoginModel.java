package my.gcu.models;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * Model class for user login information.
 * This class contains the username and password fields along with validation constraints.
 */
public class LoginModel {

    @NotNull(message = "Username is required")
    @Size(min = 3, max = 32, message = "Username must be between 3 and 32 characters")
    private String username;

    @NotNull(message = "Password is required")
    @Size(min = 5, max = 32, message = "Password must be between 5 and 32 characters")
    private String password;

    /**
     * Gets the username of the user.
     * 
     * @return the username
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * Sets the username of the user.
     * 
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the password of the user.
     * 
     * @return the password
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Sets the password of the user.
     * 
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
}

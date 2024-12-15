package my.gcu.models;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import my.gcu.data.entity.UserEntity;

/**
 * Represents a user model with validation annotations to ensure proper user data.
 * This class is used to handle the user's details like name, username, password, etc.
 */
public class UserModel
{   
    private Integer id;

    @NotNull
    private String role;

    @NotNull(message="First Name is a required field")
    @Size(min=1, max=32, message="First Name must be between 1-32 characters long")
    private String firstName;

    @NotNull(message="Last Name is a required field")
    @Size(min=1, max=32, message="Last Name must be between 1-32 characters long")
    private String lastName;

    @NotNull(message="Username is a required field")
    @Size(min=3, max=32, message="Username must be between 3-32 characters long")
    private String username;

    @NotNull(message="Password is a required field")
    @Size(min=5, max=32, message="Password must be between 5-32 characters long")
    private String password;

    @NotNull(message="E-Mail is a required field")
    @Size(min=1, max=32, message="E-Mail must be between 1-32 characters long")
    private String emailAddress;

    @NotNull(message="Phone Number is a required field")
    @Size(min=1, max=32, message="Phone Number must be between 1-32 characters long")
    private String phoneNumber;

    /**
     * Default constructor for UserModel.
     */
    public UserModel(){}

    /**
     * Constructor to create a UserModel from a UserEntity.
     * 
     * @param user the UserEntity to convert into a UserModel
     */
    public UserModel(UserEntity user)
    {
        this.id = user.getId();
        this.role = user.getRole();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.emailAddress = user.getEmailAddress();
        this.phoneNumber = user.getPhoneNumber();
    }

    /**
     * Gets the user ID.
     * 
     * @return the user ID
     */
    public Integer getId()
    {
        return id;
    }

    /**
     * Sets the user ID.
     * 
     * @param id the user ID to set
     */
    public void setId(int id)
    {
        this.id = id;
    }

    /**
     * Gets the user role.
     * 
     * @return the user role
     */
    public String getRole() {
        return role;
    }

    /**
     * Sets the user role.
     * 
     * @param role the user role to set
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * Gets the user's first name.
     * 
     * @return the user's first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the user's first name.
     * 
     * @param firstName the user's first name to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets the user's last name.
     * 
     * @return the user's last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the user's last name.
     * 
     * @param lastName the user's last name to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets the user's username.
     * 
     * @return the user's username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the user's username.
     * 
     * @param userName the user's username to set
     */
    public void setUsername(String userName) {
        this.username = userName;
    }

    /**
     * Gets the user's password.
     * 
     * @return the user's password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the user's password.
     * 
     * @param password the user's password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the user's email address.
     * 
     * @return the user's email address
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     * Sets the user's email address.
     * 
     * @param emailAddress the user's email address to set
     */
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    /**
     * Gets the user's phone number.
     * 
     * @return the user's phone number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets the user's phone number.
     * 
     * @param phoneNumber the user's phone number to set
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}

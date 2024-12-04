package my.gcu.models;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import my.gcu.data.entity.UserEntity;

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

    public UserModel(){}

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

    public Integer getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String userName) {
        this.username = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

}

package my.gcu.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import my.gcu.data.entity.UserEntity;
import my.gcu.data.repository.UserRepository;
import my.gcu.interfaces.ServiceInterface;
import my.gcu.models.LoginModel;

/**
 * Service that handles user login validation and authentication.
 * It interacts with the user repository to verify user credentials and manage login states.
 */
@Service
public class LoginService implements ServiceInterface
{
    @Autowired
    private UserRepository userRepo;

    private boolean isLoggedIn;
    private boolean isAdmin;

    /**
     * Initializes the LoginService by setting the login states.
     * This method is called when the bean is created.
     */
    @Override
    public void init()
    {
        System.out.println("Login Service Bean Initialized");
        isLoggedIn = false;
        isAdmin = false;
    }

    /**
     * Destroys the LoginService and logs a message when the bean is destroyed.
     */
    @Override
    public void destroy()
    {
        System.out.println("Login Service Bean Destroyed");
    }

    /**
     * Validates the login credentials by checking for errors and matching the username and password.
     * 
     * @param bindingResult the result of the form binding that contains validation errors
     * @param loginModel the login model that contains user input
     * @return the name of the view to display (either "admin", "products", or "login")
     */
    public String validateLogin(BindingResult bindingResult, LoginModel loginModel)
    {
        if (bindingResult.hasErrors())
            return "login";
    
        // Check credentials in the database
        Optional<UserEntity> user = userRepo.findByUsername(loginModel.getUsername());
    
        // Validate if the user exists and the password matches
        if (user.isPresent() && user.get().getPassword().equals(loginModel.getPassword()))
        {
            isLoggedIn = true;

            // Check if the username is "admin" to assign admin privileges
            if ("admin".equalsIgnoreCase(user.get().getUsername()))
            {
                isAdmin = true;
                return "admin";  
            }

            // For non-admin users
            isAdmin = false;
            return "products";  
        }
        
        // Invalid credentials
        bindingResult.reject("loginError", "Invalid credentials"); 
        return "login";
    }
    
    /**
     * Checks if the user has admin privileges and adds the status to the model.
     * 
     * @param model the model to add the admin status to
     * @return the updated model with the admin status
     */
    public Model modelCheckAdmin(Model model)
    {
        model.addAttribute("isAdmin", this.getIsAdmin());
        return model;
    }

    /**
     * Gets the login status of the user.
     * 
     * @return true if the user is logged in, false otherwise
     */
    public boolean getIsLoggedIn()
    {
        return isLoggedIn;
    }

    /**
     * Gets the admin status of the user.
     * 
     * @return true if the user has admin privileges, false otherwise
     */
    public boolean getIsAdmin()
    {
        return isAdmin;
    }

    /**
     * Sets the admin status of the user.
     * 
     * @param isAdmin the admin status to set
     */
    public void setIsAdmin(boolean isAdmin)
    {
        this.isAdmin = isAdmin;
    }
}

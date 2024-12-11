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

@Service
public class LoginService implements ServiceInterface
{
    @Autowired
    private UserRepository userRepo;

    private boolean isLoggedIn;
    private boolean isAdmin;

    @Override
    public void init()
    {
        System.out.println("Login Service Bean Initialized");
        isLoggedIn = false;
        isAdmin = false;
    }

    @Override
    public void destroy()
    {
        System.out.println("Login Service Bean Destroyed");
    }

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
    
    public Model modelCheckAdmin(Model model)
    {
        model.addAttribute("isAdmin", this.getIsAdmin());
        return model;
    }

    public boolean getIsLoggedIn()
    {
        return isLoggedIn;
    }

    public boolean getIsAdmin()
    {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin)
    {
        this.isAdmin = isAdmin;
    }
}

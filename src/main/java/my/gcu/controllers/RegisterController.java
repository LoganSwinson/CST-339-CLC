package my.gcu.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;
import my.gcu.models.UserModel;
import my.gcu.services.RegisterService;

/**
 * Controller for handling user registration.
 */
@Controller
public class RegisterController {

    @Autowired 
    private RegisterService registerServiceBean;

    /**
     * Displays the registration form.
     * 
     * @param model the {@link Model} object used to pass data to the view
     * @return the name of the register view template
     */
    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("title", "Register Form");
        model.addAttribute("userModel", new UserModel());
        return "register";
    }

    /**
     * Processes the user registration form submission.
     * 
     * @param userModel the {@link UserModel} object containing user input
     * @param bindingResult the {@link BindingResult} object for validation results
     * @param model the {@link Model} object used to pass data to the view
     * @return the name of the view template to display next
     */
    @PostMapping("/register")
    public String doRegister(@Valid @ModelAttribute("userModel") UserModel userModel, BindingResult bindingResult, Model model) {
        // If there are validation errors, redisplay the registration form
        if (bindingResult.hasErrors()) {
            System.out.println("ERROR IN REGISTRATION");
            System.out.println(bindingResult.getFieldErrors());
            model.addAttribute("title", "Register Form");
            return "register";
        }

        System.out.println("SUCCESS IN REGISTRATION");
        registerServiceBean.addUser(userModel);

        // Redirect to login page after successful registration
        return "redirect:/login";
    }
}

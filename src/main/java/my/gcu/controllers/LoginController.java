package my.gcu.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller for handling the login page.
 */
@Controller
public class LoginController {

    /**
     * Displays the login page.
     * 
     * @param model the {@link Model} object used to pass data to the view
     * @return the name of the login view template
     */
    @GetMapping("/login")
    public String display(Model model) {
        model.addAttribute("title", "Login Page");
        return "login";
    }
}

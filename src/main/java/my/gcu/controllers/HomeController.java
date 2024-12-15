package my.gcu.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller for handling the home page.
 */
@Controller
public class HomeController {

    /**
     * Displays the home page.
     * 
     * @param model the {@link Model} object used to pass data to the view
     * @return the name of the home view template
     */
    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("title", "Home Page");
        return "home";  // Return the home.html template
    }
}

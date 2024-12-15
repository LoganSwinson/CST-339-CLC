package my.gcu.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller for handling the cart page.
 */
@Controller
public class CartController {

    /**
     * Displays the cart page.
     * 
     * @param model the {@link Model} object used to pass data to the view
     * @return the name of the cart view template
     */
    @GetMapping("/cart")
    public String home(Model model) {
        model.addAttribute("title", "Cart");
        return "cart";
    }
}

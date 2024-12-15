package my.gcu.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller responsible for handling the support page.
 */
@Controller
public class SupportController {

    /**
     * Displays the support page.
     * Adds the title attribute to the model for use in the support view.
     *
     * @param model the model to add attributes to
     * @return the name of the support view template
     */
    @GetMapping("/support")
    public String home(Model model) {
        model.addAttribute("title", "Support");
        return "support";
    }
}

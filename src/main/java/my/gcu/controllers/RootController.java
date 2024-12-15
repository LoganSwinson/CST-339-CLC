package my.gcu.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller responsible for handling the root URL ("/") and redirecting to the home page.
 */
@Controller
public class RootController {

    /**
     * Redirects requests to the root URL ("/") to the home page ("/home").
     *
     * @return a redirect to the "/home" page
     */
    @GetMapping("/")
    public String root() {
        // When the user goes to the "root" page, redirect to the "home" page
        return "redirect:/home";
    }
}

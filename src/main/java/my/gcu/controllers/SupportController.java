package my.gcu.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SupportController {

    @GetMapping("/support")
    public String home(Model model) {
        model.addAttribute("title", "Support");
        return "support";
    }
}
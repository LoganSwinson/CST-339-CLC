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

@Controller
public class RegisterController
{
    @Autowired private RegisterService registerServiceBean;

    @GetMapping("/register")
    public String register(Model model)
    {
        model.addAttribute("title", "Register Form");
        model.addAttribute("userModel", new UserModel());

        return "register";
    }

    @PostMapping("/register")   
    public String doRegister(@Valid @ModelAttribute("userModel") UserModel userModel, BindingResult bindingResult, Model model)
    {
        // If the input data was invalid, refresh the page and refresh the page
        if (bindingResult.hasErrors())
        {
            System.out.println("ERROR IN REGISTRATION");
            System.out.println(bindingResult.getFieldErrors());
            model.addAttribute("title", "Register Form");
            return "register";
        }

        System.out.println("SUCCESS IN REGISTRATION");
        registerServiceBean.addUser(userModel);
    
        // Redirect to login after successful registration
        return "redirect:/login";
    }
}

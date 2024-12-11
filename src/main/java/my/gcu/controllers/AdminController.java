package my.gcu.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;
import my.gcu.models.ProductModel;
import my.gcu.services.LoginService;
import my.gcu.services.ProductService;

@Controller
public class AdminController
{
    @Autowired private LoginService loginServiceBean;
    @Autowired private ProductService productServiceBean;

    @GetMapping("/admin")
    public String home(Model model)
    {
         Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Check if the user is authenticated and has the ROLE_ADMIN authority
        boolean isAdmin = authentication != null && authentication.getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"));

        model.addAttribute("isAdmin", isAdmin); // Pass admin status to the view
        model.addAttribute("title", "Admin Page");

        if (isAdmin) {
            // Add product-related attributes only for admins
            model.addAttribute("newProduct", new ProductModel());
            model.addAttribute("updatedProduct", new ProductModel());
            model.addAttribute("productList", productServiceBean.getProductList());
        }

        return "admin";
    }

    Model setDefaultAttributes(Model model)
    {
        // The loginServiceBean from login is reused to check if the user has logged in as an admin before
        model = loginServiceBean.modelCheckAdmin(model);
        model.addAttribute("title", "Admin Page");

        // Adds empty products to use for creation or updates
        model.addAttribute("newProduct", new ProductModel());
        model.addAttribute("updatedProduct", new ProductModel());
        model.addAttribute("productList", productServiceBean.getProductList());

        return model;
    }

    public String createOrUpdateProduct(ProductModel product, BindingResult result, Model model)
    {
        model = setDefaultAttributes(model);

        // If errors occur, return to the product creation page
        if (result.hasErrors())
        {   System.out.println("\nERROR");
            return "admin";
        }

        // The save method from the Crud Repository will also update a product if an ID has already been used
        productServiceBean.addProduct(product);
        // Used to display products
        model.addAttribute("productList", productServiceBean.getProductList());

        return "redirect:/admin";
    }

    @PostMapping("/admin/createProduct")
    public String createProduct(@Valid @ModelAttribute("newProduct") ProductModel product, BindingResult result, Model model)
    {
        return createOrUpdateProduct(product, result, model);
    }

    @PostMapping("/admin/updateProduct")
    public String updateProduct(@Valid @ModelAttribute("updatedProduct") ProductModel product, BindingResult result, Model model)
    {
        return createOrUpdateProduct(product, result, model);
    }

    @PostMapping("/admin/deleteProduct/{id}")
    public String deleteProduct(@PathVariable("id") Integer id)
    {
        productServiceBean.deleteProductById(id);
        return "redirect:/admin";
    }
}

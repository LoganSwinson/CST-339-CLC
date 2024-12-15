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

/**
 * Controller for handling admin-related functionalities. This includes viewing
 * the admin page, managing products, and handling product creation, updates,
 * and deletions.
 */
@Controller
public class AdminController {

    /**
     * Service for managing login and admin status.
     */
    @Autowired
    private LoginService loginServiceBean;

    /**
     * Service for managing products.
     */
    @Autowired
    private ProductService productServiceBean;

    /**
     * Displays the admin page. If the user is an admin, additional
     * product-related data is shown.
     *
     * @param model the {@link Model} object for passing data to the view
     * @return the name of the admin view template
     */
    @GetMapping("/admin")
    public String home(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Check if the user is authenticated and has the ROLE_ADMIN authority
        boolean isAdmin = authentication != null && authentication.getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"));
        loginServiceBean.setIsAdmin(isAdmin); // Store admin status
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

    /**
     * Sets default attributes for the admin model. Reuses the login service to
     * check if the user is an admin.
     *
     * @param model the {@link Model} object for passing data to the view
     * @return the updated {@link Model} object
     */
    Model setDefaultAttributes(Model model) {
        model = loginServiceBean.modelCheckAdmin(model);
        model.addAttribute("title", "Admin Page");
        model.addAttribute("newProduct", new ProductModel());
        model.addAttribute("updatedProduct", new ProductModel());
        model.addAttribute("productList", productServiceBean.getProductList());
        return model;
    }

    /**
     * Handles product creation or updates. If there are validation errors, it
     * redirects back to the admin page.
     *
     * @param product the product data from the form
     * @param result the {@link BindingResult} for validation errors
     * @param model the {@link Model} object for passing data to the view
     * @return the name of the admin view template or a redirect URL
     */
    public String createOrUpdateProduct(ProductModel product, BindingResult result, Model model) {
        model = setDefaultAttributes(model);

        if (result.hasErrors()) {
            System.out.println("\nERROR");
            return "admin";
        }

        productServiceBean.addProduct(product);
        model.addAttribute("productList", productServiceBean.getProductList());
        return "redirect:/admin";
    }

    /**
     * Handles the creation of a new product.
     *
     * @param product the new product data
     * @param result the {@link BindingResult} for validation errors
     * @param model the {@link Model} object for passing data to the view
     * @return a redirect URL to the admin page
     */
    @PostMapping("/admin/createProduct")
    public String createProduct(@Valid @ModelAttribute("newProduct") ProductModel product, BindingResult result, Model model) {
        return createOrUpdateProduct(product, result, model);
    }

    /**
     * Handles updating an existing product.
     *
     * @param product the updated product data
     * @param result the {@link BindingResult} for validation errors
     * @param model the {@link Model} object for passing data to the view
     * @return a redirect URL to the admin page
     */
    @PostMapping("/admin/updateProduct")
    public String updateProduct(@Valid @ModelAttribute("updatedProduct") ProductModel product, BindingResult result, Model model) {
        return createOrUpdateProduct(product, result, model);
    }

    /**
     * Handles the deletion of a product by its ID.
     *
     * @param id the ID of the product to be deleted
     * @return a redirect URL to the admin page
     */
    @PostMapping("/admin/deleteProduct/{id}")
    public String deleteProduct(@PathVariable("id") Integer id) {
        productServiceBean.deleteProductById(id);
        return "redirect:/admin";
    }
}

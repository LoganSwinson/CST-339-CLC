package my.gcu.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import my.gcu.services.LoginService;
import my.gcu.services.ProductService;

@Controller
public class ProductsController 
{
    @Autowired private LoginService loginServiceBean;
    @Autowired private ProductService productServiceBean; 

    @GetMapping("products")
    public String displayProducts(Model model)
    {
        model.addAttribute("title", "Products");
        model.addAttribute("isLoggedIn", loginServiceBean.getIsLoggedIn());
        model.addAttribute("productList", productServiceBean.getProductList());
        return "products";
    }
}

package my.gcu.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import my.gcu.models.ProductModel;
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

      @GetMapping("/service/{id}")
    public ResponseEntity<ProductModel> getProductById(@PathVariable("id") Integer id) {
        ProductModel product = productServiceBean.getProductById(id);
        if (product != null) {
            return new ResponseEntity<>(product, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

package my.gcu.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import my.gcu.models.ProductModel;
import my.gcu.models.UserModel;
import my.gcu.services.LoginService;
import my.gcu.services.ProductService;
import my.gcu.services.UserService;

@Controller
public class RestAPIController
{
    @Autowired private LoginService loginServiceBean;
    @Autowired private ProductService productServiceBean;
    @Autowired private UserService userServiceBean;

    @GetMapping("/services/product/{id}")
    public ResponseEntity<ProductModel> getProductById(@PathVariable("id") Integer id)
    {
        if (productServiceBean.getProductById(id) == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(productServiceBean.getProductById(id), HttpStatus.OK);  
    }

    @GetMapping("/services/product/all")
    public ResponseEntity<List<ProductModel>> getAllProducts()
    {
        if (productServiceBean.getProductList() == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        
        return new ResponseEntity<>(productServiceBean.getProductList(), HttpStatus.OK);
    }

    @GetMapping("/services/user/{id}")
    public ResponseEntity<UserModel> getUserById(@PathVariable("id") Integer id)
    {
        // If you are not logged in as an admin, or the id retrieved is for an admin or if there was no user for the id return null
        if (loginServiceBean.getIsAdmin() == false || userServiceBean.getUserById(id) == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        
        return new ResponseEntity<>(userServiceBean.getUserById(id), HttpStatus.OK);
    }

    @GetMapping("/services/user/all")
    public ResponseEntity<List<UserModel>> getAllUsers()
    {
        // If you are not logged in as an admin or if there are no non-admin users to return, the list of users is not found
        if (loginServiceBean.getIsAdmin() == false || userServiceBean.getUserList() == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(userServiceBean.getUserList(), HttpStatus.OK);
    }
}

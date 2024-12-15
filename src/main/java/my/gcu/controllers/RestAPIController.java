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

/**
 * REST API controller that provides endpoints for accessing product and user data.
 */
@Controller
public class RestAPIController {

    @Autowired 
    private LoginService loginServiceBean;
    @Autowired 
    private ProductService productServiceBean;
    @Autowired 
    private UserService userServiceBean;

    /**
     * Retrieves a product by its ID.
     *
     * @param id the ID of the product
     * @return a {@link ResponseEntity} containing the {@link ProductModel} if found, or {@code NOT_FOUND} if not
     */
    @GetMapping("/services/product/{id}")
    public ResponseEntity<ProductModel> getProductById(@PathVariable("id") Integer id) {
        if (productServiceBean.getProductById(id) == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(productServiceBean.getProductById(id), HttpStatus.OK);
    }

    /**
     * Retrieves all products.
     *
     * @return a {@link ResponseEntity} containing a list of {@link ProductModel} objects if found, or {@code NOT_FOUND} if no products exist
     */
    @GetMapping("/services/product/all")
    public ResponseEntity<List<ProductModel>> getAllProducts() {
        if (productServiceBean.getProductList() == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(productServiceBean.getProductList(), HttpStatus.OK);
    }

    /**
     * Retrieves a user by their ID.
     *
     * @param id the ID of the user
     * @return a {@link ResponseEntity} containing the {@link UserModel} if found and the request is authorized, or {@code NOT_FOUND} otherwise
     */
    @GetMapping("/services/user/{id}")
    public ResponseEntity<UserModel> getUserById(@PathVariable("id") Integer id) {
        // Ensure the requester is an admin and the user exists
        if (!loginServiceBean.getIsAdmin() || userServiceBean.getUserById(id) == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(userServiceBean.getUserById(id), HttpStatus.OK);
    }

    /**
     * Retrieves all users.
     *
     * @return a {@link ResponseEntity} containing a list of {@link UserModel} objects if the request is authorized and users exist, or {@code NOT_FOUND} otherwise
     */
    @GetMapping("/services/user/all")
    public ResponseEntity<List<UserModel>> getAllUsers() {
        // Ensure the requester is an admin and there are users to return
        if (!loginServiceBean.getIsAdmin() || userServiceBean.getUserList() == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(userServiceBean.getUserList(), HttpStatus.OK);
    }
}

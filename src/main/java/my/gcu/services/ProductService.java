package my.gcu.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import my.gcu.data.entity.ProductEntity;
import my.gcu.data.repository.ProductRepository;
import my.gcu.interfaces.ServiceInterface;
import my.gcu.models.ProductModel;

/**
 * Service that handles operations related to products.
 * It interacts with the product repository to add, retrieve, and delete products.
 */
public class ProductService implements ServiceInterface
{
    @Autowired
    private ProductRepository productRepo;

    /**
     * Initializes the ProductService.
     * Currently does nothing but can be used for future setup tasks.
     */
    @Override
    public void init()
    {
        return;
    }

    /**
     * Destroys the ProductService.
     * Currently does nothing but can be used for cleanup tasks.
     */
    @Override
    public void destroy()
    {
        return;
    }

    /**
     * Adds a new product to the database.
     * Converts the ProductModel to a ProductEntity and saves it.
     * 
     * @param product the product to be added
     */
    public void addProduct(ProductModel product)
    {
        productRepo.save(new ProductEntity(product));
    }

    /**
     * Retrieves a list of all products in the database.
     * Converts the list of ProductEntities to ProductModels.
     * 
     * @return a list of all products
     */
    public List<ProductModel> getProductList() {
        var productList = new ArrayList<ProductModel>();
        var productEntities = productRepo.findAll();
    
        for (ProductEntity entity : productEntities) {
            productList.add(new ProductModel(entity));
        }
    
        return productList;
    }

    /**
     * Deletes a product by its ID.
     * 
     * @param id the ID of the product to be deleted
     */
    public void deleteProductById(Integer id)
    {
        productRepo.deleteById(id);
    }

    /**
     * Retrieves a product by its ID.
     * 
     * @param id the ID of the product to be retrieved
     * @return the product if found, or null if not found
     */
    public ProductModel getProductById(Integer id) {
        Optional<ProductEntity> productEntity = productRepo.findById(id);
        return productEntity.map(ProductModel::new).orElse(null);
    }
}

package my.gcu.data.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import my.gcu.models.ProductModel;

/**
 * Entity class that maps to the "PRODUCTS" table in the database.
 * Represents a product entity with various properties like name, price, description, and quantity.
 */
@Table("PRODUCTS")
public class ProductEntity {

    @Id
    private Integer id;
    private String name;
    private double price;
    private String description;
    private int quantity;

    /**
     * Default constructor for ProductEntity.
     */
    public ProductEntity() {}

    /**
     * Constructs a ProductEntity from a ProductModel.
     *
     * @param product the ProductModel object to convert to ProductEntity
     */
    public ProductEntity(ProductModel product) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.description = product.getDescription();
        this.quantity = product.getQuantity();
    }

    /**
     * Gets the ID of the product.
     *
     * @return the ID of the product
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the ID of the product.
     *
     * @param id the ID to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Gets the name of the product.
     *
     * @return the name of the product
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the product.
     *
     * @param productName the name to set
     */
    public void setName(String productName) {
        this.name = productName;
    }

    /**
     * Gets the price of the product.
     *
     * @return the price of the product
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the price of the product.
     *
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Gets the description of the product.
     *
     * @return the description of the product
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the product.
     *
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the quantity of the product.
     *
     * @return the quantity of the product
     */
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * Sets the quantity of the product.
     *
     * @param quantity the quantity to set
     */
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}

package my.gcu.models;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import my.gcu.data.entity.ProductEntity;

/**
 * Model class representing a product with validation rules.
 * This class contains the product's name, price, description, and quantity with validation constraints.
 */
public class ProductModel
{
    private Integer id;

    @NotEmpty(message = "Product name is required")
    private String name;

    @Positive(message = "Price must be positive")
    private double price;

    private String description;

    @PositiveOrZero(message = "Quantity must be greater than or equal to zero")
    private Integer quantity;

    /**
     * Default constructor for ProductModel.
     */
    public ProductModel() {}

    /**
     * Constructor to create a ProductModel from a ProductEntity.
     * 
     * @param product the ProductEntity to convert into a ProductModel
     */
    public ProductModel(ProductEntity product)
    {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.description = product.getDescription();
        this.quantity = product.getQuantity();
    }

    /**
     * Gets the product ID.
     * 
     * @return the product ID
     */
    public Integer getId()
    {
        return id;
    }

    /**
     * Sets the product ID.
     * 
     * @param id the product ID to set
     */
    public void setId(Integer id)
    {
        this.id = id;
    }

    /**
     * Gets the product name.
     * 
     * @return the product name
     */
    public String getName()
    {
        return name;
    }

    /**
     * Sets the product name.
     * 
     * @param name the product name to set
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Gets the product price.
     * 
     * @return the product price
     */
    public double getPrice()
    {
        return price;
    }

    /**
     * Sets the product price.
     * 
     * @param price the product price to set
     */
    public void setPrice(double price)
    {
        this.price = price;
    }

    /**
     * Gets the product description.
     * 
     * @return the product description
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * Sets the product description.
     * 
     * @param description the product description to set
     */
    public void setDescription(String description)
    {
        this.description = description;
    }

    /**
     * Gets the product quantity.
     * 
     * @return the product quantity
     */
    public Integer getQuantity()
    {
        return quantity;
    }

    /**
     * Sets the product quantity.
     * 
     * @param quantity the product quantity to set
     */
    public void setQuantity(Integer quantity)
    {
        this.quantity = quantity;
    }
}

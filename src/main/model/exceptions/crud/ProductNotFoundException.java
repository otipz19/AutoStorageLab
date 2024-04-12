package main.model.exceptions.crud;

import main.model.valueObjects.ProductName;

import java.util.UUID;

/**
 * The ProductNotFoundException class represents exceptions that occur when a product is not found in the system.
 * It extends the CrudException class.
 */
public class ProductNotFoundException extends CrudException {
    /**
     * Constructs a new ProductNotFoundException with the given product name.
     *
     * @param name the name of the product that was not found
     */
    public ProductNotFoundException(ProductName name) {
        super("Product with specified name was not found: " + name);
    }

    /**
     * Constructs a new ProductNotFoundException with the given product id.
     *
     * @param id the id of the product that was not found
     */
    public ProductNotFoundException(UUID id) {
        super("Product with specified id was not found: " + id);
    }
}
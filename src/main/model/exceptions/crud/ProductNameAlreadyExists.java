package main.model.exceptions.crud;

import main.model.valueObjects.ProductName;

/**
 * The ProductNameAlreadyExists class represents exceptions that occur when a product name already exists in the system.
 * It extends the CrudException class.
 */
public class ProductNameAlreadyExists extends CrudException {
    /**
     * Constructs a new ProductNameAlreadyExists exception with the given product name.
     *
     * @param name the name of the product that already exists
     */
    public ProductNameAlreadyExists(ProductName name) {
        super("Product with specified name already exists: " + name);
    }
}
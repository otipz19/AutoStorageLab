package main.model.exceptions.crud;

import main.model.valueObjects.ProductName;

import java.util.UUID;

public class ProductNotFoundException extends CrudException {
    public ProductNotFoundException(ProductName name) {
        super("Product with specified name was not found: " + name);
    }

    public ProductNotFoundException(UUID id) {
        super("Product with specified id was not found: " + id);
    }
}

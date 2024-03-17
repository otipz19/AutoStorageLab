package main.model.exceptions.crud;

import main.model.valueObjects.ProductName;

public class ProductNameAlreadyExists extends CrudException {
    public ProductNameAlreadyExists(ProductName name) {
        super("Product with specified name already exists: " + name);
    }
}

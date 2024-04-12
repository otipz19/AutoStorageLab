package main.model.valueObjects;

import lombok.Getter;

import java.io.Serializable;

/**
 * The ProductName class represents the name of a product in the application.
 * It extends the Name class and implements the Serializable interface.
 */
@Getter
public class ProductName extends Name implements Serializable {
    /**
     * Constructs a new ProductName with the given value.
     *
     * @param value the value of the product name
     */
    public ProductName(String value) {
        super(value);
    }
}
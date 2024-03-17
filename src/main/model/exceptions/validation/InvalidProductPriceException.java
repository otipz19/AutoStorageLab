package main.model.exceptions.validation;

public class InvalidProductPriceException extends ValidationException{
    public InvalidProductPriceException(double value) {
        super("Product price must be not negative. Provided value: " + value);
    }
}

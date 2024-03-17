package main.model.exceptions.validation;

public class InvalidProductAmountException extends ValidationException {
    public InvalidProductAmountException(int value) {
        super("Product amount must be not negative. Provided value: " + value);
    }
}

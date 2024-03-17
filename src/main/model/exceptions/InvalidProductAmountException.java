package main.model.exceptions;

public class InvalidProductAmountException extends ValidationException {
    public InvalidProductAmountException(int value) {
        super("Product amount must be not negative. Provided value: " + value);
    }
}

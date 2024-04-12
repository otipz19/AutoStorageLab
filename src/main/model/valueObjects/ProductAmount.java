package main.model.valueObjects;

import lombok.Getter;
import main.model.exceptions.validation.InvalidProductAmountException;

import java.io.Serializable;

/**
 * The ProductAmount class represents the amount of a product in the application.
 * It implements the Serializable interface.
 */
@Getter
public class ProductAmount implements Serializable {
    private final int value;

    /**
     * Constructs a new ProductAmount with the given value.
     *
     * @param value the value of the product amount
     */
    public ProductAmount(int value) {
        validate(value);
        this.value = value;
    }

    /**
     * Checks if the given value is valid.
     *
     * @param value the value to check
     * @return true if the value is valid, false otherwise
     */
    public static boolean isValid(int value) {
        return value >= 0;
    }

    /**
     * Checks if the given string value is valid.
     *
     * @param str the string value to check
     * @return true if the string value is valid, false otherwise
     */
    public static boolean isValid(String str) {
        try {
            return isValid(Integer.valueOf(str));
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    /**
     * Validates the given value.
     *
     * @param value the value to validate
     * @throws InvalidProductAmountException if the value is not valid
     */
    private void validate(int value) {
        if (!isValid(value)) {
            throw new InvalidProductAmountException(value);
        }
    }

    /**
     * Checks if this ProductAmount is equal to the given object.
     *
     * @param other the object to compare with
     * @return true if the given object is a ProductAmount and its value is equal to this ProductAmount's value, false otherwise
     */
    @Override
    public boolean equals(Object other) {
        if (other instanceof ProductAmount otherAmount) {
            return otherAmount.value == this.value;
        }
        return false;
    }

    /**
     * Returns the hash code of this ProductAmount.
     *
     * @return the hash code of this ProductAmount
     */
    @Override
    public int hashCode() {
        return Integer.hashCode(value);
    }

    /**
     * Returns the string representation of this ProductAmount.
     *
     * @return the string representation of this ProductAmount
     */
    @Override
    public String toString() {
        return Integer.toString(value);
    }
}
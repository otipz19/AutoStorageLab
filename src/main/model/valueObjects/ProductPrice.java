package main.model.valueObjects;

import lombok.Getter;
import main.model.exceptions.validation.InvalidProductPriceException;

import java.io.Serializable;

/**
 * The ProductPrice class represents the price of a product in the application.
 * It implements the Serializable interface.
 */
@Getter
public class ProductPrice implements Serializable {
    private final double value;

    /**
     * Constructs a new ProductPrice with the given value.
     *
     * @param value the value of the product price
     */
    public ProductPrice(double value) {
        validate(value);
        this.value = value;
    }

    /**
     * Checks if the given value is valid.
     *
     * @param value the value to check
     * @return true if the value is valid, false otherwise
     */
    public static boolean isValid(double value) {
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
            return isValid(Double.valueOf(str));
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    /**
     * Validates the given value.
     *
     * @param value the value to validate
     * @throws InvalidProductPriceException if the value is not valid
     */
    private void validate(double value) {
        if (!isValid(value)) {
            throw new InvalidProductPriceException(value);
        }
    }

    /**
     * Checks if this ProductPrice is equal to the given object.
     *
     * @param other the object to compare with
     * @return true if the given object is a ProductPrice and its value is equal to this ProductPrice's value, false otherwise
     */
    @Override
    public boolean equals(Object other) {
        if (other instanceof ProductPrice otherPrice) {
            return this.value == otherPrice.value;
        }
        return false;
    }

    /**
     * Returns the hash code of this ProductPrice.
     *
     * @return the hash code of this ProductPrice
     */
    @Override
    public int hashCode() {
        return Double.hashCode(value);
    }

    /**
     * Returns the string representation of this ProductPrice.
     *
     * @return the string representation of this ProductPrice
     */
    @Override
    public String toString() {
        return Double.toString(value);
    }
}
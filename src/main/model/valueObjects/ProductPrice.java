package main.model.valueObjects;

import lombok.Getter;
import main.model.exceptions.validation.InvalidProductPriceException;

import java.io.Serializable;

@Getter
public class ProductPrice implements Serializable {
    private final double value;

    public ProductPrice(double value) {
        validate(value);
        this.value = value;
    }

    public static boolean isValid(double value) {
        return value >= 0;
    }

    public static boolean isValid(String str) {
        try {
            return isValid(Double.valueOf(str));
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    private void validate(double value) {
        if (!isValid(value)) {
            throw new InvalidProductPriceException(value);
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof ProductPrice otherPrice) {
            return this.value == otherPrice.value;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Double.hashCode(value);
    }

    @Override
    public String toString() {
        return Double.toString(value);
    }
}

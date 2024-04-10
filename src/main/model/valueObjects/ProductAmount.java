package main.model.valueObjects;

import lombok.Getter;
import main.model.exceptions.validation.InvalidProductAmountException;

@Getter
public class ProductAmount {
    private final int value;

    public ProductAmount(int value) {
        validate(value);
        this.value = value;
    }

    public static boolean isValid(int value) {
        return value >= 0;
    }

    public static boolean isValid(String str) {
        try {
            return isValid(Integer.valueOf(str));
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    private void validate(int value) {
        if (!isValid(value)) {
            throw new InvalidProductAmountException(value);
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof ProductAmount otherAmount) {
            return otherAmount.value == this.value;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(value);
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }
}

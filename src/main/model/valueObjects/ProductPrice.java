package main.model.valueObjects;

import lombok.Getter;
import main.model.exceptions.InvalidProductPriceException;

@Getter
public class ProductPrice {
    private final double value;

    public ProductPrice(double value){
        validate(value);
        this.value = value;
    }

    private void validate(double value) {
        if(value < 0){
            throw new InvalidProductPriceException(value);
        }
    }

    @Override
    public boolean equals(Object other){
        if(other instanceof ProductPrice otherPrice){
            return this.value == otherPrice.value;
        }
        return false;
    }

    @Override
    public int hashCode(){
        return Double.hashCode(value);
    }

    @Override
    public String toString(){
        return Double.toString(value);
    }
}

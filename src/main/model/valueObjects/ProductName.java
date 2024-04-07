package main.model.valueObjects;

import lombok.Getter;

@Getter
public class ProductName extends Name{
    public ProductName(String value) {
        super(value);
    }
}

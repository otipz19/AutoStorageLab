package main.model.valueObjects;

import lombok.Getter;

import java.io.Serializable;

@Getter
public class ProductName extends Name implements Serializable {
    public ProductName(String value) {
        super(value);
    }
}

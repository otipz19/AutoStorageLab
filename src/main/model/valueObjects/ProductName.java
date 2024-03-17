package main.model.valueObjects;

import lombok.Getter;
import main.model.exceptions.InvalidRecordNameException;

@Getter
public class ProductName extends Name{
    public ProductName(String value) {
        super(value);
    }
}

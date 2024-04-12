package main.model.valueObjects;

import java.io.Serializable;

public class ManufacturerName extends Name implements Serializable {
    public ManufacturerName(String value) {
        super(value);
    }
}

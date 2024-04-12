package main.model.valueObjects;

import java.io.Serializable;

/**
 * The ManufacturerName class represents the name of a manufacturer in the application.
 * It extends the Name class and implements the Serializable interface.
 */
public class ManufacturerName extends Name implements Serializable {
    /**
     * Constructs a new ManufacturerName with the given value.
     *
     * @param value the value of the manufacturer name
     */
    public ManufacturerName(String value) {
        super(value);
    }
}
package main.model.valueObjects;

import java.io.Serializable;

/**
 * The GroupName class represents the name of a group in the application.
 * It extends the Name class and implements the Serializable interface.
 */
public class GroupName extends Name implements Serializable {
    /**
     * Constructs a new GroupName with the given value.
     *
     * @param value the value of the group name
     */
    public GroupName(String value) {
        super(value);
    }
}
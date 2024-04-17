package main.model.valueObjects;

import lombok.Getter;
import main.model.exceptions.validation.InvalidRecordNameException;
import main.model.guard.Guard;

import java.io.Serializable;
import java.util.regex.Pattern;

/**
 * The Name class represents a name in the application.
 * It is an abstract class that implements the Serializable interface.
 */
@Getter
public abstract class Name implements Serializable {
    private final static Pattern VALID_PATTERN = Pattern.compile("[\\p{IsLatin}\\p{IsCyrillic}\"'-]+(\\s+[\\p{IsLatin}\\p{IsCyrillic}\"'-]+)*");
    private final String value;

    /**
     * Constructs a new Name with the given value.
     *
     * @param value the value of the name
     */
    public Name(String value){
        Guard.againstNullOrBlank(value);
        validate(value);
        this.value = value.trim();
    }

    /**
     * Checks if the given value is valid.
     *
     * @param value the value to check
     * @return true if the value is valid, false otherwise
     */
    public static boolean isValid(String value){
        return VALID_PATTERN.matcher(value).matches();
    }

    /**
     * Validates the given value.
     *
     * @param value the value to validate
     * @throws InvalidRecordNameException if the value is not valid
     */
    private static void validate(String value) {
        if(!isValid(value)){
            throw new InvalidRecordNameException(value);
        }
    }

    /**
     * Checks if this Name is equal to the given object.
     *
     * @param other the object to compare with
     * @return true if the given object is a Name and its value is equal to this Name's value, false otherwise
     */
    @Override
    public boolean equals(Object other){
        if(other instanceof Name otherName){
            return this.value.equals(otherName.value);
        }
        return false;
    }

    /**
     * Returns the hash code of this Name.
     *
     * @return the hash code of this Name
     */
    @Override
    public int hashCode(){
        return value.hashCode();
    }

    /**
     * Returns the string representation of this Name.
     *
     * @return the string representation of this Name
     */
    @Override
    public String toString(){
        return value;
    }
}
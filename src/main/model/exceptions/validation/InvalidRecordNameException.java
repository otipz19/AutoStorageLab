package main.model.exceptions.validation;

public class InvalidRecordNameException extends ValidationException {
    public InvalidRecordNameException(String value) {
        super("Record name must contain only letters. Provided value: " + value);
    }
}

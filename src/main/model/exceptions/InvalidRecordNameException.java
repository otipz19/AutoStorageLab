package main.model.exceptions;

public class InvalidRecordNameException extends ValidationException {
    public InvalidRecordNameException(String value) {
        super("Record name must contain only letters. Provided value: " + value);
    }
}

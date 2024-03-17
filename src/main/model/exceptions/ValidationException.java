package main.model.exceptions;

public class ValidationException extends RuntimeException {
    public ValidationException(String msg){
        super(msg);
    }
}

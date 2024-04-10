package main.ui.exceptions;

public class InvalidFormInputException extends RuntimeException {
    public InvalidFormInputException(Exception inner){
        super("Invalid form input! " + inner.getMessage());
    }
}
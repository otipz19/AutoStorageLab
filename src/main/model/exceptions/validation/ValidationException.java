package main.model.exceptions.validation;

import main.model.exceptions.DomainException;

public class ValidationException extends DomainException {
    public ValidationException(String msg){
        super(msg);
    }
}

package main.model.exceptions.crud;

import main.model.exceptions.DomainException;

/**
 * The CrudException class represents exceptions that occur during CRUD operations.
 * It extends the DomainException class.
 */
public class CrudException extends DomainException {
    /**
     * Constructs a new CrudException with the given message.
     *
     * @param msg the message of the exception
     */
    public CrudException(String msg){
        super(msg);
    }
}
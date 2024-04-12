package main.model.exceptions;

/**
 * The DomainException class represents exceptions that occur in the domain layer of the application.
 * It extends the RuntimeException class.
 */
public class DomainException extends RuntimeException{
    /**
     * Constructs a new DomainException with the given message.
     *
     * @param msg the message of the exception
     */
    public DomainException(String msg){
        super(msg);
    }
}
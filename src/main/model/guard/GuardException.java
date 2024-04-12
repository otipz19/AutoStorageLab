package main.model.guard;

import main.model.exceptions.DomainException;

/**
 * The GuardException class represents exceptions that occur when a guard condition is violated.
 * It extends the DomainException class.
 */
public class GuardException extends DomainException {
    /**
     * Constructs a new GuardException with the given message.
     *
     * @param msg the message of the exception
     */
    public GuardException(String msg) {
        super("Guard: " + msg);
    }
}
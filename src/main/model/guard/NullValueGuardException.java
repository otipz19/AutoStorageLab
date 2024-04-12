package main.model.guard;

/**
 * The NullValueGuardException class represents exceptions that occur when a null value is used where it's not accepted.
 * It extends the GuardException class.
 */
public class NullValueGuardException extends GuardException {
    /**
     * Constructs a new NullValueGuardException with a predefined message.
     */
    public NullValueGuardException() {
        super("Null pointer is not accepted as a value");
    }
}
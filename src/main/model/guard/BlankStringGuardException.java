package main.model.guard;

/**
 * The BlankStringGuardException class represents exceptions that occur when a blank string is used where it's not accepted.
 * It extends the GuardException class.
 */
public class BlankStringGuardException extends GuardException {
    /**
     * Constructs a new BlankStringGuardException with a predefined message.
     */
    public BlankStringGuardException() {
        super("Blank string is not accepted as a value");
    }
}
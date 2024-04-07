package main.model.guard;

public class NullValueGuardException extends GuardException {
    public NullValueGuardException() {
        super("Null pointer is not accepted as a value");
    }
}

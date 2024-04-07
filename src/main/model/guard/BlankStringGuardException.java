package main.model.guard;

public class BlankStringGuardException extends GuardException {
    public BlankStringGuardException() {
        super("Blank string is not accepted as a value");
    }
}

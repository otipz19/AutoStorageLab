package main.model.guard;

import main.model.exceptions.DomainException;

public class GuardException extends DomainException {
    public GuardException(String msg) {
        super("Guard: " + msg);
    }
}

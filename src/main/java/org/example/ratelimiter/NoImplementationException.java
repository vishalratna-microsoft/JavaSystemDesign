package org.example.ratelimiter;

public class NoImplementationException extends RuntimeException {

    public NoImplementationException() {
        super("Method implementation does not exist");
    }
}
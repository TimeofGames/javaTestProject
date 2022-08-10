package com.example.football.error;

public class AccreditationException extends RuntimeException {
    public AccreditationException() {
        super();
    }

    public AccreditationException(String message) {
        super(message);
    }

    public AccreditationException(String message, Throwable cause) {
        super(message, cause);
    }
}

package dev.andrylat.game.javarush_game.engine.service.exception;

public class InvalidStateException extends RuntimeException {
    public InvalidStateException() {
        super();
    }

    public InvalidStateException(String message) {
        super(message);
    }

    public InvalidStateException(String message, Throwable cause) {
        super(message, cause);
    }
}

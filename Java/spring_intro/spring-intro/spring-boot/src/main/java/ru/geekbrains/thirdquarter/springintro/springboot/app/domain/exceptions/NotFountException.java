package ru.geekbrains.thirdquarter.springintro.springboot.app.domain.exceptions;

public class NotFountException extends RuntimeException {
    public NotFountException() {
        super();
    }

    public NotFountException(String message) {
        super(message);
    }

    public NotFountException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFountException(Throwable cause) {
        super(cause);
    }

    protected NotFountException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

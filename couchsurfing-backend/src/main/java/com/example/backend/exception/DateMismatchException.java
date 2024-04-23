package com.example.backend.exception;

public class DateMismatchException extends ApiException {

    public DateMismatchException() {
    }

    public DateMismatchException(String message) {
        super(message);
    }

    public DateMismatchException(String message, Throwable cause) {
        super(message, cause);
    }

}

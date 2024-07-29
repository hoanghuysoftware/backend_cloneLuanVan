package com.software.exception;

public class NullEntityInputException extends RuntimeException{
    public NullEntityInputException(String message) {
        super(message);
    }

    public NullEntityInputException(String message, Throwable cause) {
        super(message, cause);
    }
}

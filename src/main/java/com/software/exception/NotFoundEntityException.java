package com.software.exception;

public class NotFoundEntityException extends RuntimeException{
    public NotFoundEntityException(String message) {
        super(message);
    }

    public NotFoundEntityException(String message, Throwable cause) {
        super(message, cause);
    }
}

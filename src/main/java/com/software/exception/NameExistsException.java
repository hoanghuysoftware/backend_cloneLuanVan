package com.software.exception;

import java.io.Serial;

public class NameExistsException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 1L;
    public NameExistsException(String message) {
        super(message);
    }

    public NameExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}

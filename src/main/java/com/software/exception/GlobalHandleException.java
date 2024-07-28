package com.software.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalHandleException {
    @ExceptionHandler(NameExistsException.class)
    public ResponseEntity<ExceptionMessage> handleNameException(Exception ex){
        return new ResponseEntity<>(ExceptionMessage.builder()
                .status("Conflicting")
                .message(ex.getMessage())
                .build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundEntityException.class)
    public ResponseEntity<ExceptionMessage> handleNotFoundException(Exception ex){
        return new ResponseEntity<>(ExceptionMessage.builder()
                .status("Not Found")
                .message(ex.getMessage())
                .build(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DateException.class)
    public ResponseEntity<ExceptionMessage> handleDateException(Exception ex){
        return new ResponseEntity<>(ExceptionMessage.builder()
                .status("Invalid Date")
                .message(ex.getMessage())
                .build(), HttpStatus.BAD_REQUEST);
    }
}

package com.software.utils;

import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;

@Component
public class ErrorsInputMessage {
    public List<String> getMessages (BindingResult result){
        return result.getFieldErrors().stream()
                .map(FieldError::getDefaultMessage)
                .toList();
    }
}

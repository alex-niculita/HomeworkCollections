package com.example.hwcollections.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class MissingParametersException extends RuntimeException{
    public MissingParametersException(String message) {
        super(message);
    }
}

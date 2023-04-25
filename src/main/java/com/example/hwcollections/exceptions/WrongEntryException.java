package com.example.hwcollections.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class WrongEntryException extends RuntimeException{
    public WrongEntryException(String message) {
        super(message);
    }
}

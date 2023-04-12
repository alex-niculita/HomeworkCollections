package com.example.hwcollections.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;


@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Helllooooo")
public class EmployeeAlreadyAddedException1 extends ResponseStatusException {

    public EmployeeAlreadyAddedException1(HttpStatusCode status, String reason) {
        super(status, reason);
    }
}

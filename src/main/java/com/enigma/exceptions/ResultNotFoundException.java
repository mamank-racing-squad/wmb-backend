package com.enigma.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResultNotFoundException extends RuntimeException{
    public ResultNotFoundException(String message) {
        super(message);
    }

    public ResultNotFoundException() {
        super("Sorry, we can't to find any data that match");
    }
}
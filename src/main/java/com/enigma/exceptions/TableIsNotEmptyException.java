package com.enigma.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class TableIsNotEmptyException extends RuntimeException{
    public TableIsNotEmptyException(String message) {
        super(message);
    }

    public TableIsNotEmptyException() {
        super("Sorry, the Table is not Empty");
    }
}

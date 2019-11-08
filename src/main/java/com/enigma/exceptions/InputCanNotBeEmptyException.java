package com.enigma.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InputCanNotBeEmptyException extends RuntimeException {
    public InputCanNotBeEmptyException(String message) {
        super(message);
    }

    public InputCanNotBeEmptyException() {
        super("Sorry, data have to Exist");
    }
}

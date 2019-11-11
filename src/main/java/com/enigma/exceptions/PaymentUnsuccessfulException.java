package com.enigma.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class PaymentUnsuccessfulException extends RuntimeException{
    public PaymentUnsuccessfulException() {
        super("Sorry, the money you entered is not enough.");
    }
}

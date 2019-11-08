package com.enigma.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class NotAccordingToCapacityException extends RuntimeException{
    public NotAccordingToCapacityException(String message){
        super(message);
    }
    public NotAccordingToCapacityException(){
        super("Sorry, Total costumer not according to table capacity");
    }
}

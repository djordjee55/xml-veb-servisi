package com.tim123.vaccinationportal.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ExistDbException extends RuntimeException{
    public ExistDbException(String message) {
        super(message);
    }
}

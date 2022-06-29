package com.tim123.vaccinationportal.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InterestAlreadyExists extends RuntimeException {
    public InterestAlreadyExists(String message) {
        super(message);
    }

}

package com.itaxi.server.exception.joiner;

import com.itaxi.server.exception.ITaxiException;

import org.springframework.http.HttpStatus;

public class JoinerException extends ITaxiException {
    public JoinerException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }
}

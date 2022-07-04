package com.itaxi.server.exception.post;

import com.itaxi.server.exception.ITaxiException;

import org.springframework.http.HttpStatus;

public class PostException extends ITaxiException {
    public PostException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }
}

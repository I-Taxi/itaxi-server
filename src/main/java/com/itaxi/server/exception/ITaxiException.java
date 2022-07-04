package com.itaxi.server.exception;

import org.springframework.http.HttpStatus;

public abstract class ITaxiException extends RuntimeException {
    private final HttpStatus httpStatus;

    public ITaxiException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {return httpStatus;}
}

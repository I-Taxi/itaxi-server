package com.itaxi.server.exception.stopover;

import com.itaxi.server.exception.ITaxiException;
import org.springframework.http.HttpStatus;

public class StopoverException extends ITaxiException {
    public StopoverException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }
}

package com.itaxi.server.exception.place;

import com.itaxi.server.exception.ITaxiException;
import org.springframework.http.HttpStatus;

public class PlaceException extends ITaxiException {
    public PlaceException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }
}

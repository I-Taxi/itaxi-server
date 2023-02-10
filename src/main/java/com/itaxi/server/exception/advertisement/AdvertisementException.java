package com.itaxi.server.exception.advertisement;

import com.itaxi.server.exception.ITaxiException;
import org.springframework.http.HttpStatus;

public class AdvertisementException extends ITaxiException {
    public AdvertisementException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }
}

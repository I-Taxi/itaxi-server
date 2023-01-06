package com.itaxi.server.exception.ktx;

import com.itaxi.server.exception.ITaxiException;
import org.springframework.http.HttpStatus;

public class KTXException extends ITaxiException {
    public KTXException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }
}

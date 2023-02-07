package com.itaxi.server.exception.history;

import com.itaxi.server.exception.ITaxiException;
import org.springframework.http.HttpStatus;

public class HistoryException extends ITaxiException {
    public HistoryException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }
}

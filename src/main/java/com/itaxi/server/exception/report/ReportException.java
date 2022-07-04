package com.itaxi.server.exception.report;

import com.itaxi.server.exception.ITaxiException;

import org.springframework.http.HttpStatus;

public class ReportException extends ITaxiException {
    public ReportException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }
}

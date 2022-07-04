package com.itaxi.server.exception.notice;

import com.itaxi.server.exception.ITaxiException;

import org.springframework.http.HttpStatus;

public class NoticeException extends ITaxiException {
    public NoticeException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }
}

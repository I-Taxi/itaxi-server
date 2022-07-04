package com.itaxi.server.exception.member;

import com.itaxi.server.exception.ITaxiException;

import org.springframework.http.HttpStatus;

public class MemberException extends ITaxiException {
    public MemberException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }
}

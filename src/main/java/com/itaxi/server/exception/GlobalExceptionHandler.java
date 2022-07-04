package com.itaxi.server.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ITaxiException.class)
    public ResponseEntity<ExceptionResponse> swHackertionException(ITaxiException e) {
        return ResponseEntity.status(e.getHttpStatus())
                             .body(new ExceptionResponse(e.getMessage()));
    }
}
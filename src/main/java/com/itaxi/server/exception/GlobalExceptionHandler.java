package com.itaxi.server.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(ITaxiException.class)
    public ResponseEntity<ExceptionResponse> iTaxiException(ITaxiException e) {

        if (e.getHttpStatus().is4xxClientError()) {
            logger.info("Client Error : " + e.getMessage());
        } else if (e.getHttpStatus().is5xxServerError()) {
            logger.error("Server Error : " + e.getMessage());
        }

        return ResponseEntity.status(e.getHttpStatus())
                             .body(new ExceptionResponse(e.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> commonException(Exception e) {

        logger.error("Unknown Exception : " + e.getMessage());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                             .body(new ExceptionResponse(e.getMessage()));
    }
}
package com.itaxi.server.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.format.DateTimeParseException;

@ControllerAdvice
public class GlobalExceptionHandler extends Throwable {
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

        logger.error("Unknown Exception : " + e.getClass());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                             .body(new ExceptionResponse("unknown error"));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ExceptionResponse> httpMessageNotReadableException(HttpMessageNotReadableException e) {
        logger.error("HttpMessageNotReadableException : " + e.getMessage());
        // 단지 LocalDateTime뿐만이 아니라 Json으로 넘어온 데이터들 중 parse가 안되면 나오는 거임
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ExceptionResponse("데이터 포맷이 틀립니다."));
    }
}
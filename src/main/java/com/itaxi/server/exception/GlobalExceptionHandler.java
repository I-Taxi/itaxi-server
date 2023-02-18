package com.itaxi.server.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.format.DateTimeParseException;

@ControllerAdvice
public class GlobalExceptionHandler extends Throwable {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    // TODO: User-Agent Flutter Application으로 전환 (flutter 딴의 header 설정도 확인)

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

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                             .body(new ExceptionResponse("unknown error"));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ExceptionResponse> httpMessageNotReadableException(HttpMessageNotReadableException e) {
        logger.error("HttpMessageNotReadableException : " + e.getMessage());
        // Json으로 넘어온 데이터들 중 parse가 안되면 나오는 것
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ExceptionResponse("데이터 포맷이 틀립니다."));
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ExceptionResponse> dataIntegrityViolationException(DataIntegrityViolationException e){
        logger.error("DataIntegrityViolationException :"+e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ExceptionResponse("중복이 될 수 없는 자료를 포함하고 있습니다."));
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ExceptionResponse> nullPointerException(NullPointerException e){
        logger.error("NullPointerException :"+e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ExceptionResponse("Null인 데이터를 불러올 수 없습니다."));
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ExceptionResponse>  httpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e){
        logger.error("HttpRequestMethodNotSupportedException :"+e.getMessage());
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
                .body(new ExceptionResponse("지원하지 않는 메소드 입니다."));
    }


}
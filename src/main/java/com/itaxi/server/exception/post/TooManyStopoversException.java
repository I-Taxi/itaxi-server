package com.itaxi.server.exception.post;

import com.itaxi.server.exception.Message;
import org.springframework.http.HttpStatus;

public class TooManyStopoversException extends PostException{
    public TooManyStopoversException(HttpStatus internalServerError) {
        super(Message.POST_TOO_MANY_STOPOVERS, HttpStatus.BAD_REQUEST);
    }
}

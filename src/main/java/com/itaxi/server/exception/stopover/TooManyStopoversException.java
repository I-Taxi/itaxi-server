package com.itaxi.server.exception.stopover;

import com.itaxi.server.exception.Message;
import com.itaxi.server.exception.post.PostException;
import org.springframework.http.HttpStatus;

public class TooManyStopoversException extends StopoverException {
    public TooManyStopoversException() {
        super(Message.POST_TOO_MANY_STOPOVERS, HttpStatus.BAD_REQUEST);
    }
}

package com.itaxi.server.exception.post;

import com.itaxi.server.exception.Message;
import org.springframework.http.HttpStatus;

public class PostTimeOutException extends PostException {
    public PostTimeOutException(HttpStatus internalServerError) {
        super(Message.POST_TIMEOUT, HttpStatus.BAD_REQUEST);
    }
}

package com.itaxi.server.exception.post;

import com.itaxi.server.exception.Message;
import org.springframework.http.HttpStatus;

public class PostTimeOverException extends PostException {
    public PostTimeOverException() {
        super(Message.POST_TIME_OVER, HttpStatus.BAD_REQUEST);
    }
}

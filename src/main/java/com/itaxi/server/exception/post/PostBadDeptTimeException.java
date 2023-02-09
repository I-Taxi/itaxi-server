package com.itaxi.server.exception.post;

import com.itaxi.server.exception.Message;
import org.springframework.http.HttpStatus;

public class PostBadDeptTimeException extends PostException{
    public PostBadDeptTimeException() {
        super(Message.POST_BAD_DEPT_TIME, HttpStatus.BAD_REQUEST);
    }
}

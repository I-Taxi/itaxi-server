package com.itaxi.server.exception.post;

import com.itaxi.server.exception.Message;
import org.springframework.http.HttpStatus;

public class PostBadDeptTimeToChangeException extends PostException{
    public PostBadDeptTimeToChangeException() {
        super(Message.POST_BAD_DEPT_TIME_TO_CHANGE, HttpStatus.BAD_REQUEST);
    }
}

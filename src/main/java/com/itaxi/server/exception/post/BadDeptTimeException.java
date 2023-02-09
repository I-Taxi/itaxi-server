package com.itaxi.server.exception.post;

import com.itaxi.server.exception.Message;
import org.springframework.http.HttpStatus;

public class BadDeptTimeException extends PostException{
    public BadDeptTimeException() {
        super(Message.DEPT_TIME_WRONG, HttpStatus.BAD_REQUEST);
    }
}

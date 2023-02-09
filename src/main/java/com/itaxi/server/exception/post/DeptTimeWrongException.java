package com.itaxi.server.exception.post;

import com.itaxi.server.exception.Message;
import org.springframework.http.HttpStatus;

public class DeptTimeWrongException extends PostException{
    public DeptTimeWrongException() {
        super(Message.DEPT_TIME_WRONG, HttpStatus.BAD_REQUEST);
    }
}

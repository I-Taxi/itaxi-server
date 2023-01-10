package com.itaxi.server.exception.post;

import com.itaxi.server.exception.Message;
import org.springframework.http.HttpStatus;

public class DeptTimeWrongException extends PostException{
    public DeptTimeWrongException(HttpStatus httpStatus) {
        super(Message.DEPT_TIME_WRONG, httpStatus);
    }
}

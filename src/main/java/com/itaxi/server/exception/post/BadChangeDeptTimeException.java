package com.itaxi.server.exception.post;

import com.itaxi.server.exception.Message;
import org.springframework.http.HttpStatus;

public class BadChangeDeptTimeException extends PostException{
    public BadChangeDeptTimeException() {
        super(Message.CANNOT_CHANGE_DEPT_TIME, HttpStatus.BAD_REQUEST);
    }
}

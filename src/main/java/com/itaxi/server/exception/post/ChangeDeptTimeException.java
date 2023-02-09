package com.itaxi.server.exception.post;

import com.itaxi.server.exception.Message;
import org.springframework.http.HttpStatus;

public class ChangeDeptTimeException extends PostException{
    public ChangeDeptTimeException() {
        super(Message.CANNOT_CHANGE_DEPT_TIME, HttpStatus.BAD_REQUEST);
    }
}

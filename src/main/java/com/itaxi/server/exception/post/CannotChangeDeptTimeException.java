package com.itaxi.server.exception.post;

import com.itaxi.server.exception.Message;
import org.springframework.http.HttpStatus;

public class CannotChangeDeptTimeException extends PostException{
    public CannotChangeDeptTimeException(HttpStatus httpStatus) {
        super(Message.CANNOT_CHANGE_DEPT_TIME, httpStatus);
    }
}

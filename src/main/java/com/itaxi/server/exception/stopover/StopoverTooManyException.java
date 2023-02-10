package com.itaxi.server.exception.stopover;

import com.itaxi.server.exception.Message;
import org.springframework.http.HttpStatus;

public class StopoverTooManyException extends StopoverException {
    public StopoverTooManyException() {
        super(Message.STOPOVER_TOO_MANY, HttpStatus.BAD_REQUEST);
    }
}

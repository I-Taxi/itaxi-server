package com.itaxi.server.exception.stopover;

import com.itaxi.server.exception.Message;
import org.springframework.http.HttpStatus;

public class StopoverNotFoundException extends StopoverException {
    public StopoverNotFoundException() {
        super(Message.STOPOVER_NOT_FOUND, HttpStatus.BAD_REQUEST);
    }
}

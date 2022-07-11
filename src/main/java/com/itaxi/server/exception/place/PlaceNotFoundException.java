package com.itaxi.server.exception.place;

import com.itaxi.server.exception.Message;
import org.springframework.http.HttpStatus;

public class PlaceNotFoundException extends PlaceException {
    public PlaceNotFoundException() {
        super(Message.NOTICE_NOT_FOUND, HttpStatus.BAD_REQUEST);
    }
}

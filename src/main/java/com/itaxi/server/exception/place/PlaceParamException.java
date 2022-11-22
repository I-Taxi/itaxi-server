package com.itaxi.server.exception.place;

import com.itaxi.server.exception.Message;
import org.springframework.http.HttpStatus;

public class PlaceParamException extends PlaceException {
    public PlaceParamException() {
        super(Message.PLACE_PARAM_NOT_FOUND, HttpStatus.BAD_REQUEST);
    }
}

package com.itaxi.server.exception.place;


import com.itaxi.server.exception.Message;
import org.springframework.http.HttpStatus;

import static com.itaxi.server.exception.Message.BANNER_PLACE_CNT_MINUS_EXCEPTION;

public class PlaceNotNullException extends PlaceException {
    public PlaceNotNullException() {super(Message.PLACE_NOT_NULL_EXCEPTION, HttpStatus.BAD_REQUEST);}
}

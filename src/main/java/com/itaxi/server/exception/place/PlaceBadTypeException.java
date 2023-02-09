package com.itaxi.server.exception.place;

import com.itaxi.server.exception.Message;
import org.springframework.http.HttpStatus;

public class PlaceBadTypeException extends PlaceException {
    public PlaceBadTypeException() {super(Message.PLACE_BAD_TYPE, HttpStatus.BAD_REQUEST);}
}

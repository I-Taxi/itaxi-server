package com.itaxi.server.exception.place;


import com.itaxi.server.exception.Message;
import org.springframework.http.HttpStatus;

public class PlaceEmptyException extends PlaceException {
    public PlaceEmptyException() {super(Message.PLACE_EMPTY_PLACE, HttpStatus.BAD_REQUEST);}
}

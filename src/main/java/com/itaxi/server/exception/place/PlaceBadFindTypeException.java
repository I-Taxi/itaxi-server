package com.itaxi.server.exception.place;

import com.itaxi.server.exception.Message;
import org.springframework.http.HttpStatus;

public class PlaceBadFindTypeException extends PlaceException{
    public PlaceBadFindTypeException() {super(Message.PLACE_FIND_TYPE_EXCEPTION, HttpStatus.BAD_REQUEST);}
}

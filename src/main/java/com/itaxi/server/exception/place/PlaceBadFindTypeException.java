package com.itaxi.server.exception.place;

import com.itaxi.server.exception.Message;
import org.springframework.http.HttpStatus;

public class PlaceBadFindTypeException extends PlaceException{
    public PlaceBadFindTypeException() {super(Message.PLACE_BAD_FIND_TYPE, HttpStatus.BAD_REQUEST);}
}

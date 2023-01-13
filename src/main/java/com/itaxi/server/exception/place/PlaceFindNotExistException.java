package com.itaxi.server.exception.place;

import com.itaxi.server.exception.Message;
import org.springframework.http.HttpStatus;

public class PlaceFindNotExistException extends PlaceException{
    public PlaceFindNotExistException(HttpStatus httpStatus) {super(Message.PLACE_FIND_NOT_EXIST_EXCEPTION, httpStatus);}
}

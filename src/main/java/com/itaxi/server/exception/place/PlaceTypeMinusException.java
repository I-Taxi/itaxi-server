package com.itaxi.server.exception.place;


import com.itaxi.server.exception.Message;
import org.springframework.http.HttpStatus;



public class PlaceTypeMinusException extends PlaceException {
    public PlaceTypeMinusException(HttpStatus httpStatus) {super(Message.PLACE_TYPE_MINUS_EXCEPTION, httpStatus);}
}

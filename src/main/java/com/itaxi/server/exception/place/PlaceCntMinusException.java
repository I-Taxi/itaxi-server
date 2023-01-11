package com.itaxi.server.exception.place;

import com.itaxi.server.exception.Message;
import com.itaxi.server.exception.banner.BannerException;
import org.springframework.http.HttpStatus;


public class PlaceCntMinusException extends PlaceException {
    public PlaceCntMinusException(HttpStatus httpStatus) {super(Message.PLACE_CNT_MINUS_EXCEPTION, httpStatus);}
}

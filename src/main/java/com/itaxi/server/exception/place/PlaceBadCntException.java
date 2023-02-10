package com.itaxi.server.exception.place;

import com.itaxi.server.exception.Message;
import org.springframework.http.HttpStatus;

public class PlaceBadCntException extends PlaceException {
    public PlaceBadCntException() {super(Message.PLACE_BAD_CNT, HttpStatus.BAD_REQUEST);}
}

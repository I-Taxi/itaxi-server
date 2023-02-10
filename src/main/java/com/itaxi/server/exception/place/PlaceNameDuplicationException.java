package com.itaxi.server.exception.place;

import com.itaxi.server.exception.Message;
import org.springframework.http.HttpStatus;

public class PlaceNameDuplicationException extends PlaceException{
    public PlaceNameDuplicationException(){super(Message.KTX_PLACE_DUPLICATION_EXCEPTION, HttpStatus.BAD_REQUEST);}
}

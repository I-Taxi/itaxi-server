package com.itaxi.server.exception.ktx;

import com.itaxi.server.exception.Message;
import org.springframework.http.HttpStatus;

public class SamePlaceException extends KTXException{
    public SamePlaceException(HttpStatus httpStatus) {
        super(Message.KTX_SAME_PLACE, httpStatus);
    }
}

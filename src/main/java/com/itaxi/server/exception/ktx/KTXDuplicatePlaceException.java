package com.itaxi.server.exception.ktx;

import com.itaxi.server.exception.Message;
import org.springframework.http.HttpStatus;

public class KTXDuplicatePlaceException extends KTXException{
    public KTXDuplicatePlaceException() {
        super(Message.KTX_SAME_PLACE, HttpStatus.BAD_REQUEST);
    }
}

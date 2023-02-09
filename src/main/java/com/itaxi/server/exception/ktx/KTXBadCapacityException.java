package com.itaxi.server.exception.ktx;

import com.itaxi.server.exception.Message;
import org.springframework.http.HttpStatus;

public class KTXBadCapacityException extends KTXException{
    public KTXBadCapacityException() {
        super(Message.KTX_WRONG_CAPACITY, HttpStatus.BAD_REQUEST);
    }
}

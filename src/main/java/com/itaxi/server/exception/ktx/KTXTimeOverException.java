package com.itaxi.server.exception.ktx;

import com.itaxi.server.exception.Message;
import org.springframework.http.HttpStatus;

public class KTXTimeOverException extends KTXException{
    public KTXTimeOverException() {
        super(Message.KTX_TIME_OVER, HttpStatus.BAD_REQUEST);
    }
}

package com.itaxi.server.exception.ktx;

import com.itaxi.server.exception.Message;
import org.springframework.http.HttpStatus;

public class KTXBadDateException extends KTXException{
    public KTXBadDateException() {
        super(Message.KTX_BAD_DATE, HttpStatus.BAD_REQUEST);
    }
}

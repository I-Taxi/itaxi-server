package com.itaxi.server.exception.ktx;

import com.itaxi.server.exception.Message;
import org.springframework.http.HttpStatus;

public class KTXTimeOutException extends KTXException{
    public KTXTimeOutException(HttpStatus httpStatus) {
        super(Message.KTX_TIMEOUT, httpStatus);
    }
}

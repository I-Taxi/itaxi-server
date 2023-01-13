package com.itaxi.server.exception.ktx;

import com.itaxi.server.exception.Message;
import org.springframework.http.HttpStatus;

public class BadCntException extends KTXException{
    public BadCntException(HttpStatus httpStatus) {
        super(Message.KTX_BAD_CNT, httpStatus);
    }
}

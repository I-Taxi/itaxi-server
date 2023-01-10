package com.itaxi.server.exception.ktx;

import com.itaxi.server.exception.Message;
import org.springframework.http.HttpStatus;

public class BadDateException extends KTXException{
    public BadDateException(HttpStatus httpStatus) {
        super(Message.KTX_BAD_DATE, httpStatus);
    }
}
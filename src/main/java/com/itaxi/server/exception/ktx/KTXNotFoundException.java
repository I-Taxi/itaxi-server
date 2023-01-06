package com.itaxi.server.exception.ktx;

import com.itaxi.server.exception.Message;
import org.springframework.http.HttpStatus;

public class KTXNotFoundException extends KTXException{
    public KTXNotFoundException(HttpStatus httpStatus) {
        super(Message.KTX_NOT_FOUND, httpStatus);
    }
}

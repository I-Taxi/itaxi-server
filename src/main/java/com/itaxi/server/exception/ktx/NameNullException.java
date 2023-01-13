package com.itaxi.server.exception.ktx;

import com.itaxi.server.exception.Message;
import org.springframework.http.HttpStatus;

public class NameNullException extends KTXException{
    public NameNullException(HttpStatus httpStatus) {
        super(Message.KTX_NAME_NULL, httpStatus);
    }
}

package com.itaxi.server.exception.ktx;

import com.itaxi.server.exception.Message;
import org.springframework.http.HttpStatus;

public class WrongCapacityException extends KTXException{
    public WrongCapacityException(HttpStatus httpStatus) {
        super(Message.KTX_WRONG_CAPACITY, httpStatus);
    }
}

package com.itaxi.server.exception.ktx;

import com.itaxi.server.exception.Message;
import org.springframework.http.HttpStatus;

public class KTXNameEmptyException extends KTXException{
    public KTXNameEmptyException() {
        super(Message.KTX_NAME_NULL, HttpStatus.BAD_REQUEST);
    }
}

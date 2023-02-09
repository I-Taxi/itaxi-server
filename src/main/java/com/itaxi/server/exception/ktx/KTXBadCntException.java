package com.itaxi.server.exception.ktx;

import com.itaxi.server.exception.Message;
import org.springframework.http.HttpStatus;

public class KTXBadCntException extends KTXException{
    public KTXBadCntException() {
        super(Message.KTX_BAD_CNT, HttpStatus.BAD_REQUEST);
    }
}

package com.itaxi.server.exception.ktx;

import com.itaxi.server.exception.Message;
import org.springframework.http.HttpStatus;

public class KTXBadSaleException extends KTXException{
    public KTXBadSaleException() {super(Message.KTX_BAD_SALE, HttpStatus.BAD_REQUEST);}
}

package com.itaxi.server.exception.ktx;

import com.itaxi.server.exception.Message;
import org.springframework.http.HttpStatus;

public class KTXBadSaleException extends KTXException{
    public KTXBadSaleException() {super(Message.KTX_SALE_RANGE_EXCEPTION, HttpStatus.BAD_REQUEST);}
}

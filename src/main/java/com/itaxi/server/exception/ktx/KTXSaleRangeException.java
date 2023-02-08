package com.itaxi.server.exception.ktx;

import com.itaxi.server.exception.Message;
import org.springframework.http.HttpStatus;

public class KTXSaleRangeException extends KTXException{
    public KTXSaleRangeException(HttpStatus httpStatus) {super(Message.KTX_SALE_RANGE_EXCEPTION, httpStatus);}
}

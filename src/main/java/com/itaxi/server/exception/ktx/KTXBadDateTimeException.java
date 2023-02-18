package com.itaxi.server.exception.ktx;

import com.itaxi.server.exception.Message;
import org.springframework.http.HttpStatus;

public class KTXBadDateTimeException extends KTXException{
    public KTXBadDateTimeException(){super(Message.KTX_BAD_DATE_TIME, HttpStatus.BAD_REQUEST);}
}

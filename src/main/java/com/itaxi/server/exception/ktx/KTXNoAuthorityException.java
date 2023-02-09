package com.itaxi.server.exception.ktx;

import com.itaxi.server.exception.Message;
import org.springframework.http.HttpStatus;

public class KTXNoAuthorityException extends KTXException{
    public KTXNoAuthorityException() {super(Message.KTX_NO_AUTHORITY, HttpStatus.FORBIDDEN);}
}

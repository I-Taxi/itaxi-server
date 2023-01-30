package com.itaxi.server.exception.ktx;

import com.itaxi.server.exception.Message;
import org.springframework.http.HttpStatus;

public class KTXNoAuthorityToGetException extends KTXException{
    public KTXNoAuthorityToGetException(HttpStatus httpStatus) {super(Message.KTX_NO_AUTHORITY_TO_GET, httpStatus);}
}

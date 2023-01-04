package com.itaxi.server.exception.ktx;

import com.itaxi.server.exception.Message;
import org.springframework.http.HttpStatus;

public class KTXMemberFullException extends KTXException{
    public KTXMemberFullException(HttpStatus httpStatus) {
        super(Message.KTX_MEMBER_FULL, httpStatus);
    }
}

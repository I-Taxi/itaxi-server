package com.itaxi.server.exception.ktx;

import com.itaxi.server.exception.Message;
import org.springframework.http.HttpStatus;

public class KTXMemberFullException extends KTXException{
    public KTXMemberFullException() {
        super(Message.KTX_MEMBER_FULL, HttpStatus.BAD_REQUEST);
    }
}

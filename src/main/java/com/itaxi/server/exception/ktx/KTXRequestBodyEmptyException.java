package com.itaxi.server.exception.ktx;

import com.itaxi.server.exception.Message;
import org.springframework.http.HttpStatus;

public class KTXRequestBodyEmptyException extends KTXException {
    public KTXRequestBodyEmptyException() {
        super(Message.KTX_REQUEST_BODY_NULL, HttpStatus.BAD_REQUEST);
    }
}

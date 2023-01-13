package com.itaxi.server.exception.ktx;

import com.itaxi.server.exception.Message;
import org.springframework.http.HttpStatus;

public class KTXRequestBodyNullException extends KTXException {
    public KTXRequestBodyNullException(HttpStatus httpStatus) {
        super(Message.KTX_REQUEST_BODY_NULL, httpStatus);
    }
}

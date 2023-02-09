package com.itaxi.server.exception.ktx;

import com.itaxi.server.exception.Message;
import com.itaxi.server.exception.joiner.JoinerException;
import org.springframework.http.HttpStatus;

public class KTXJoinerNotOwnerException extends JoinerException {
    public KTXJoinerNotOwnerException() {
        super(Message.JOINER_NOT_OWNER, HttpStatus.BAD_REQUEST);
    }
}

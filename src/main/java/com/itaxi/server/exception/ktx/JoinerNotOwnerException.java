package com.itaxi.server.exception.ktx;

import com.itaxi.server.exception.Message;
import com.itaxi.server.exception.joiner.JoinerException;
import org.springframework.http.HttpStatus;

public class JoinerNotOwnerException extends JoinerException {
    public JoinerNotOwnerException(HttpStatus internalServerError) {
        super(Message.JOINER_NOT_OWNER, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

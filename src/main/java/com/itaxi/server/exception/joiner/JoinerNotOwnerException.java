package com.itaxi.server.exception.joiner;

import com.itaxi.server.exception.Message;
import com.itaxi.server.exception.joiner.JoinerException;
import org.springframework.http.HttpStatus;

public class JoinerNotOwnerException extends JoinerException {
    public JoinerNotOwnerException() {
        super(Message.JOINER_NOT_OWNER, HttpStatus.BAD_REQUEST);
    }
}

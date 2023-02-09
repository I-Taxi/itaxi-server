package com.itaxi.server.exception.joiner;

import com.itaxi.server.exception.Message;
import org.springframework.http.HttpStatus;

public class JoinerNotFoundException extends JoinerException {
    public JoinerNotFoundException() {
        super(Message.JOINER_NOT_FOUND, HttpStatus.NOT_FOUND);
    }
}

package com.itaxi.server.exception.joiner;

import com.itaxi.server.exception.Message;
import org.springframework.http.HttpStatus;

public class JoinerNotFoundException extends JoinerException {
    public JoinerNotFoundException(HttpStatus httpStatus) {
        super(Message.Joiner_NOT_FOUND, httpStatus);
    }
}

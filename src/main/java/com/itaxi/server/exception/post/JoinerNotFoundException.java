package com.itaxi.server.exception.post;

import com.itaxi.server.exception.Message;
import org.springframework.http.HttpStatus;

public class JoinerNotFoundException extends JoinerException {
    public JoinerNotFoundException(HttpStatus internalServerError) {
        super(Message.Joiner_NOT_FOUND, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

package com.itaxi.server.exception.post;

import com.itaxi.server.exception.Message;
import org.springframework.http.HttpStatus;

public class JoinerDuplicateMemberException extends PostException {
    public JoinerDuplicateMemberException(HttpStatus internalServerError) {
        super(Message.JOINER_DUPLICATE_MEMBER, HttpStatus.BAD_REQUEST);
    }
}

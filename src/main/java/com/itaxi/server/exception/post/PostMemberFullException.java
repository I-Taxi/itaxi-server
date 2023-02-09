package com.itaxi.server.exception.post;

import com.itaxi.server.exception.Message;
import org.springframework.http.HttpStatus;

public class PostMemberFullException extends PostException {
    public PostMemberFullException() {
        super(Message.POST_MEMBER_FULL, HttpStatus.BAD_REQUEST);
    }
}

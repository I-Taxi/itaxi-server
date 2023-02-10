package com.itaxi.server.exception.post;

import com.itaxi.server.exception.Message;
import org.springframework.http.HttpStatus;

public class PostBadPostTypeException extends PostException{
    public PostBadPostTypeException(){super(Message.POST_BAD_TYPE, HttpStatus.BAD_REQUEST);}
}

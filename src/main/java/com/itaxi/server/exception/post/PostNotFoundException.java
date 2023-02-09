package com.itaxi.server.exception.post;

import com.itaxi.server.exception.Message;

import org.springframework.http.HttpStatus;

public class PostNotFoundException extends PostException {
    public PostNotFoundException() { super(Message.POST_NOT_FOUND, HttpStatus.BAD_REQUEST); }
}

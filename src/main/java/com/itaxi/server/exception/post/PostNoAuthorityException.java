package com.itaxi.server.exception.post;

import com.itaxi.server.exception.Message;
import org.springframework.http.HttpStatus;

public class PostNoAuthorityException extends PostException{
    public PostNoAuthorityException() {super(Message.POST_NO_AUTHORITY_TO_GET, HttpStatus.FORBIDDEN);}
}

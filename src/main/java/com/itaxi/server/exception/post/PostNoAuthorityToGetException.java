package com.itaxi.server.exception.post;

import com.itaxi.server.exception.Message;
import org.springframework.http.HttpStatus;

public class PostNoAuthorityToGetException extends PostException{
    public PostNoAuthorityToGetException(HttpStatus httpStatus) {super(Message.POST_NO_AUTHORITY_TO_GET, httpStatus);}
}

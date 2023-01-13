package com.itaxi.server.exception.member;

import com.itaxi.server.exception.Message;
import org.springframework.http.HttpStatus;

public class FavorDuplicatedException extends MemberException{
    public FavorDuplicatedException(HttpStatus httpStatus){
        super(Message.FAVOR_JOINER_DUPLICATED, httpStatus);
    }
}

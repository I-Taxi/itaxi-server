package com.itaxi.server.exception.favorite;

import com.itaxi.server.exception.Message;
import com.itaxi.server.exception.member.MemberException;
import org.springframework.http.HttpStatus;

public class FavorDuplicatedException extends MemberException {
    public FavorDuplicatedException(HttpStatus httpStatus){
        super(Message.FAVOR_JOINER_DUPLICATED, httpStatus);
    }
}

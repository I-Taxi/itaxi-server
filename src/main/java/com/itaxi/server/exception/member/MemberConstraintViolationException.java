package com.itaxi.server.exception.member;

import com.itaxi.server.exception.Message;
import org.springframework.http.HttpStatus;

public class MemberConstraintViolationException extends MemberException {
    public MemberConstraintViolationException(HttpStatus httpStatus) {
        super(Message.FAVOR_JOINER_DUPLICATED, httpStatus);
    }
}
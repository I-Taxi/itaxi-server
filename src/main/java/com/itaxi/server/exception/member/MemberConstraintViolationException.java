package com.itaxi.server.exception.member;

import com.itaxi.server.exception.Message;
import org.springframework.http.HttpStatus;

public class MemberConstraintViolationException extends MemberException {
    public MemberConstraintViolationException(HttpStatus httpStatus) {
        super(Message.MEMBER_DELETE_FAILED, httpStatus);
    }
}
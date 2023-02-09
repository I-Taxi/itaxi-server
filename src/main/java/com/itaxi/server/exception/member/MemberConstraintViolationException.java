package com.itaxi.server.exception.member;

import com.itaxi.server.exception.Message;
import org.springframework.http.HttpStatus;

public class MemberConstraintViolationException extends MemberException {
    public MemberConstraintViolationException() {
        super(Message.MEMBER_CAN_NOT_DELETE, HttpStatus.METHOD_NOT_ALLOWED);
    }
}
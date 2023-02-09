package com.itaxi.server.exception.member;

import com.itaxi.server.exception.Message;
import org.springframework.http.HttpStatus;

public class MemberConstraintViolationException extends MemberException {
    public MemberConstraintViolationException() {
        super(Message.MEMBER_CONSTRANINT_VIOLATION, HttpStatus.METHOD_NOT_ALLOWED);
    }
}
package com.itaxi.server.exception.member;

import com.itaxi.server.exception.Message;
import org.springframework.http.HttpStatus;

public class MemberDeleteFailedException extends MemberException {
    public MemberDeleteFailedException(HttpStatus httpStatus) {
        super(Message.MEMBER_CONSTRAINT_VIOLATION_EXCEPTION, httpStatus);
    }
}

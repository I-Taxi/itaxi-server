package com.itaxi.server.exception.member;

import com.itaxi.server.exception.Message;
import org.springframework.http.HttpStatus;

public class MemberCreateFailedException extends MemberException {
    public MemberCreateFailedException(HttpStatus httpStatus) {
        super(Message.MEMBER_CREATE_FAILED, httpStatus);
    }
}

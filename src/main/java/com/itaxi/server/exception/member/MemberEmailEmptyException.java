package com.itaxi.server.exception.member;

import com.itaxi.server.exception.Message;
import org.springframework.http.HttpStatus;

public class MemberEmailEmptyException extends MemberException {
    public MemberEmailEmptyException() { super(Message.MEMBER_EMAIL_EMPTY, HttpStatus.BAD_REQUEST); }
}

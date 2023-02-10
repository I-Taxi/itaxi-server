package com.itaxi.server.exception.member;

import com.itaxi.server.exception.Message;
import org.springframework.http.HttpStatus;

public class MemberBadEmailException extends MemberException {
    public MemberBadEmailException() { super(Message.MEMBER_BAD_EMAIL, HttpStatus.BAD_REQUEST); }
}

package com.itaxi.server.exception.member;

import com.itaxi.server.exception.Message;

import org.springframework.http.HttpStatus;

public class MemberNotFoundException extends MemberException {
    public MemberNotFoundException() {
        super(Message.MEMBER_NOT_FOUND, HttpStatus.NOT_FOUND);
    }
}

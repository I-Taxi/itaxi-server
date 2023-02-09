package com.itaxi.server.exception.member;

import com.itaxi.server.exception.Message;
import org.springframework.http.HttpStatus;

public class MemberDeleteFailedException extends MemberException {
    public MemberDeleteFailedException() {
        super(Message.MEMBER_DELETE_FAILED, HttpStatus.BAD_REQUEST);
    }
}

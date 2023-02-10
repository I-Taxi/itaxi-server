package com.itaxi.server.exception.member;

import com.itaxi.server.exception.Message;
import org.springframework.http.HttpStatus;

public class MemberNameEmptyException extends MemberException {
    public MemberNameEmptyException() {
        super(Message.MEMBER_NAME_EMPTY, HttpStatus.BAD_REQUEST);
    }
}

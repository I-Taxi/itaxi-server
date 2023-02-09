package com.itaxi.server.exception.member;

import com.itaxi.server.exception.Message;
import org.springframework.http.HttpStatus;

public class MemberUpdateFailedException extends MemberException {
    public MemberUpdateFailedException() {
        super(Message.MEMBER_UPDATE_FAILED, HttpStatus.BAD_REQUEST);
    }
}

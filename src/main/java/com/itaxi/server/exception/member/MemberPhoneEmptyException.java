package com.itaxi.server.exception.member;

import com.itaxi.server.exception.Message;
import org.springframework.http.HttpStatus;

public class MemberPhoneEmptyException extends MemberException {
    public MemberPhoneEmptyException() {
        super(Message.MEMBER_PHONE_ISNULL, HttpStatus.BAD_REQUEST);
    }
}

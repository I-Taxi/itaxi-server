package com.itaxi.server.exception.member;

import com.itaxi.server.exception.Message;
import org.springframework.http.HttpStatus;

public class MemberPhoneNullException extends MemberException {
    public MemberPhoneNullException(HttpStatus httpStatus) {
        super(Message.MEMBER_PHONE_ISNULL, httpStatus);
    }
}

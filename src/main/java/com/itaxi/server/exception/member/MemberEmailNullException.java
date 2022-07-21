package com.itaxi.server.exception.member;

import com.itaxi.server.exception.Message;
import org.springframework.http.HttpStatus;

public class MemberEmailNullException extends MemberException {
    public MemberEmailNullException(HttpStatus httpStatus) {
        super(Message.MEMBER_EMAIL_ISNULL, httpStatus);
    }
}

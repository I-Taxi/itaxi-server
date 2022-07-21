package com.itaxi.server.exception.member;

import com.itaxi.server.exception.Message;
import org.springframework.http.HttpStatus;

public class MemberUidNullException extends MemberException {
    public MemberUidNullException(HttpStatus httpStatus) {
        super(Message.MEMBER_UID_ISNULL, httpStatus);
    }
}

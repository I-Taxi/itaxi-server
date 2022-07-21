package com.itaxi.server.exception.member;

import com.itaxi.server.exception.Message;
import org.springframework.http.HttpStatus;

public class MemberNameNullException extends MemberException {
    public MemberNameNullException(HttpStatus httpStatus) {
        super(Message.MEMBER_NAME_ISNULL, httpStatus);
    }
}

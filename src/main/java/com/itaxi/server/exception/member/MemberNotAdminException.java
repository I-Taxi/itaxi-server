package com.itaxi.server.exception.member;

import com.itaxi.server.exception.Message;
import org.springframework.http.HttpStatus;

public class MemberNotAdminException extends MemberException{
    public MemberNotAdminException(HttpStatus httpStatus) {
        super(Message.MEMBER_NOT_ADMIN, httpStatus);
    }
}

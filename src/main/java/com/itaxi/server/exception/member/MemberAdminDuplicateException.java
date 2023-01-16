package com.itaxi.server.exception.member;

import com.itaxi.server.exception.Message;
import org.springframework.http.HttpStatus;

public class MemberAdminDuplicateException extends MemberException{
    public MemberAdminDuplicateException(HttpStatus httpStatus) {
        super(Message.MEMBER_ADMIN_DUPLICATE, httpStatus);
    }
}

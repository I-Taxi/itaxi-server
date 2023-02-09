package com.itaxi.server.exception.member;

import com.itaxi.server.exception.Message;
import org.springframework.http.HttpStatus;

public class MemberAdminDuplicateException extends MemberException{
    public MemberAdminDuplicateException() {
        super(Message.MEMBER_ADMIN_DUPLICATE, HttpStatus.BAD_REQUEST);
    }
}

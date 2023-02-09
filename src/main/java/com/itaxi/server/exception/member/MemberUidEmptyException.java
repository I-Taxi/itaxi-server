package com.itaxi.server.exception.member;

import com.itaxi.server.exception.Message;
import org.springframework.http.HttpStatus;

public class MemberUidEmptyException extends MemberException {
    public MemberUidEmptyException() {
        super(Message.MEMBER_UID_ISNULL, HttpStatus.BAD_REQUEST);
    }
}

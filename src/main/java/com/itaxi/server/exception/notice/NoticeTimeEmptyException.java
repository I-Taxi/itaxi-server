package com.itaxi.server.exception.notice;

import com.itaxi.server.exception.Message;
import org.springframework.http.HttpStatus;

public class NoticeTimeEmptyException extends NoticeException{
    public NoticeTimeEmptyException() { super(Message.NOTICE_TIME_EMPTY_EXCEPTION, HttpStatus.BAD_REQUEST); }
}

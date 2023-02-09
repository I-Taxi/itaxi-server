package com.itaxi.server.exception.notice;

import com.itaxi.server.exception.Message;
import org.springframework.http.HttpStatus;

public class NoticeBadTypeException extends NoticeException{
    public NoticeBadTypeException(){super(Message.NOTICE_BAD_TYPE_EXCEPTION, HttpStatus.BAD_REQUEST);}
}

package com.itaxi.server.exception.notice;

import com.itaxi.server.exception.Message;

import org.springframework.http.HttpStatus;

public class NoticeNotFoundException extends NoticeException {
    public NoticeNotFoundException() {
        super(Message.NOTICE_NOT_FOUND, HttpStatus.NOT_FOUND);
    }
}

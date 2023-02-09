package com.itaxi.server.exception.notice;

import com.itaxi.server.exception.Message;
import org.springframework.http.HttpStatus;

public class NoticeContentEmptyException extends NoticeException {
    public NoticeContentEmptyException() {
        super(Message.NOTICE_CONTENT, HttpStatus.BAD_REQUEST);
    }
}

package com.itaxi.server.exception.notice;

import com.itaxi.server.exception.Message;
import org.springframework.http.HttpStatus;

public class NoticeTitleEmptyException extends NoticeException {
    public NoticeTitleEmptyException() {
        super(Message.NOTICE_TITLE_EMPTY, HttpStatus.BAD_REQUEST);
    }
}

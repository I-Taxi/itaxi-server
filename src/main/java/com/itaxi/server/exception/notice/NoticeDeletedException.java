package com.itaxi.server.exception.notice;

import com.itaxi.server.exception.Message;
import org.springframework.http.HttpStatus;

public class NoticeDeletedException extends NoticeException {
    public NoticeDeletedException(HttpStatus httpStatus) {
        super(Message.NOTICE_DELETED, httpStatus);
    }
}

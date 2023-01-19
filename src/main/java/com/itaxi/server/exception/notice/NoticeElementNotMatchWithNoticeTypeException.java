package com.itaxi.server.exception.notice;

import com.itaxi.server.exception.Message;
import org.springframework.http.HttpStatus;

public class NoticeElementNotMatchWithNoticeTypeException extends NoticeException{
    public NoticeElementNotMatchWithNoticeTypeException(){super(Message.NOTICE_NOTICE_ElEMENT_NOT_MATCH_WITH_NOTICE_TYPE_EXCEPTION, HttpStatus.INTERNAL_SERVER_ERROR);}
}

package com.itaxi.server.exception.report;

import com.itaxi.server.exception.Message;
import org.springframework.http.HttpStatus;

public class MemberNotWriterException extends ReportException{
    public MemberNotWriterException(HttpStatus httpStatus) {
        super(Message.MEMBER_NOT_WRITER, httpStatus);
    }
}

package com.itaxi.server.exception.report;

import com.itaxi.server.exception.Message;

import org.springframework.http.HttpStatus;

public class ReportNotFoundException extends ReportException {
    public ReportNotFoundException(HttpStatus httpStatus) {
        super(Message.REPORT_NOT_FOUND, httpStatus);
    }
}

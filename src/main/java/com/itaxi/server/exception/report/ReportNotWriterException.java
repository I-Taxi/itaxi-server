package com.itaxi.server.exception.report;

import com.itaxi.server.exception.Message;
import org.springframework.http.HttpStatus;

public class ReportNotWriterException extends ReportException{
    public ReportNotWriterException() { super(Message.REPORT_NOT_WRITER, HttpStatus.FORBIDDEN); }
}

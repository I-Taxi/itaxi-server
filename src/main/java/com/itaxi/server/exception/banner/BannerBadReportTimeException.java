package com.itaxi.server.exception.banner;

import com.itaxi.server.exception.Message;
import org.springframework.http.HttpStatus;


public class BannerBadReportTimeException extends BannerException{
    public BannerBadReportTimeException() {super(Message.BANNER_BAD_REPORT_TIME_EXCEPTION, HttpStatus.BAD_REQUEST);}
}

package com.itaxi.server.exception.banner;

import com.itaxi.server.exception.Message;
import org.springframework.http.HttpStatus;


public class BannerReportTimeException  extends BannerException{
    public BannerReportTimeException(HttpStatus httpStatus) {super(Message.BANNER_BAD_REPORT_TIME_EXCEPTION, httpStatus);}
}

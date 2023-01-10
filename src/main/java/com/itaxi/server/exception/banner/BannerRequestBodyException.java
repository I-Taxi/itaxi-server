package com.itaxi.server.exception.banner;

import com.itaxi.server.exception.Message;
import org.springframework.http.HttpStatus;

public class BannerRequestBodyException extends BannerException{
    public BannerRequestBodyException(HttpStatus httpStatus) {super(Message.BANNER_REQUEST_BODY_EXCEPTION, httpStatus);}
}

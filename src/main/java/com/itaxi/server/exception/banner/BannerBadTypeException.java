package com.itaxi.server.exception.banner;

import com.itaxi.server.exception.Message;
import org.springframework.http.HttpStatus;

public class BannerBadTypeException extends BannerException{
    public BannerBadTypeException() {
        super(Message.BANNER_BAD_TYPE_EXCEPTION, HttpStatus.BAD_REQUEST);
    }
}

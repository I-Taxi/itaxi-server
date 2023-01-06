package com.itaxi.server.exception.banner;

import com.itaxi.server.exception.Message;
import org.springframework.http.HttpStatus;

public class BannerBadWeatherStatusException extends BannerException{
    public BannerBadWeatherStatusException() {
        super(Message.BANNER_UID_EMPTY_EXCEPTION, HttpStatus.BAD_REQUEST);
    }
}

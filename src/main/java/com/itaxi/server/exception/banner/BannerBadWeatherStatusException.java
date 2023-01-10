package com.itaxi.server.exception.banner;

import com.itaxi.server.exception.Message;
import org.springframework.http.HttpStatus;

public class BannerBadWeatherStatusException extends BannerException{
    public BannerBadWeatherStatusException() {
        super(Message.BANNER_BAD_WEATHER_STATUS_EXCEPTION, HttpStatus.BAD_REQUEST);
    }
}

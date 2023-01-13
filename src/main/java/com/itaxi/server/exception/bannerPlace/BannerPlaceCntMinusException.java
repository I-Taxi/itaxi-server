package com.itaxi.server.exception.bannerPlace;

import com.itaxi.server.exception.banner.BannerException;
import org.springframework.http.HttpStatus;

import static com.itaxi.server.exception.Message.BANNER_PLACE_CNT_MINUS_EXCEPTION;


public class BannerPlaceCntMinusException extends BannerException {
    public BannerPlaceCntMinusException(HttpStatus httpStatus) {super(BANNER_PLACE_CNT_MINUS_EXCEPTION, httpStatus);}
}

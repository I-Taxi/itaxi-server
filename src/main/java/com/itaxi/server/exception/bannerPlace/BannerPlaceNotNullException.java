package com.itaxi.server.exception.bannerPlace;

import com.itaxi.server.exception.banner.BannerException;
import org.springframework.http.HttpStatus;

import static com.itaxi.server.exception.Message.BANNER_PLACE_NAME_NULL_EXCEPTION;
import static com.itaxi.server.exception.Message.BANNER_PLACE_NOT_FOUND;

public class BannerPlaceNotNullException extends BannerException {
    public BannerPlaceNotNullException(HttpStatus httpStatus) {
        super(BANNER_PLACE_NAME_NULL_EXCEPTION, httpStatus);
    }
}

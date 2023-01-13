package com.itaxi.server.exception.bannerPlace;

import com.itaxi.server.exception.banner.BannerException;
import org.springframework.http.HttpStatus;

import static com.itaxi.server.exception.Message.BANNER_NOT_FOUND;
import static com.itaxi.server.exception.Message.BANNER_PLACE_NOT_FOUND;

public class BannerPlaceNotFoundException extends BannerException {
    public BannerPlaceNotFoundException(HttpStatus httpStatus) {
        super(BANNER_PLACE_NOT_FOUND, httpStatus);
    }
}

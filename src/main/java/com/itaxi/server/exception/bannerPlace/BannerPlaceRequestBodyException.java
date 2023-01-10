package com.itaxi.server.exception.bannerPlace;
import com.itaxi.server.exception.Message;
import com.itaxi.server.exception.banner.BannerException;
import org.springframework.http.HttpStatus;



public class BannerPlaceRequestBodyException extends BannerException {
    public BannerPlaceRequestBodyException(HttpStatus httpStatus) {
        super(Message.BANNER_PLACE_REQUEST_BODY_EXCEPTION ,httpStatus);
    }
}

package com.itaxi.server.exception.banner;

import com.itaxi.server.exception.ITaxiException;
import org.springframework.http.HttpStatus;

public class BannerException  extends ITaxiException {
    public BannerException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }
}

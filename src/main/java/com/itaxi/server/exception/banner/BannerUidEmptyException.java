package com.itaxi.server.exception.banner;

import com.itaxi.server.exception.Message;
import org.springframework.http.HttpStatus;

public class BannerUidEmptyException extends BannerException {
    public BannerUidEmptyException() {
        super(Message.BANNER_UID_EMPTY_EXCEPTION, HttpStatus.BAD_REQUEST);
    }
}

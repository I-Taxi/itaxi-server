package com.itaxi.server.exception.banner;

import com.itaxi.server.exception.Message;
import org.springframework.http.HttpStatus;

public class BannerNoAuthorityException extends BannerException{
    public BannerNoAuthorityException(){
        super(Message.BANNER_NO_AUTHORITY_EXCEPTION, HttpStatus.BAD_REQUEST);
    }
}

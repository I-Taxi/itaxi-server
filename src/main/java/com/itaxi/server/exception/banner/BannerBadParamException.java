package com.itaxi.server.exception.banner;

import com.itaxi.server.exception.Message;
import org.springframework.http.HttpStatus;

public class BannerBadParamException extends BannerException{
    public BannerBadParamException() {super(Message.BANNER_BAD_PARAM_EXCEPTION, HttpStatus.BAD_REQUEST);}
}

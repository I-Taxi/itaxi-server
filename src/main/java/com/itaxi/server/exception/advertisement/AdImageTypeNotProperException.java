package com.itaxi.server.exception.advertisement;

import com.itaxi.server.exception.Message;
import org.springframework.http.HttpStatus;

public class AdImageTypeNotProperException extends AdvertisementException{
    public AdImageTypeNotProperException(){super(Message.IMAGE_TYPE_NOT_PROPER, HttpStatus.BAD_REQUEST);}
}

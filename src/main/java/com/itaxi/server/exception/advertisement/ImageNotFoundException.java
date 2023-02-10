package com.itaxi.server.exception.advertisement;

import com.itaxi.server.exception.Message;
import org.springframework.http.HttpStatus;

public class ImageNotFoundException extends AdvertisementException{
    public ImageNotFoundException() {super(Message.IMAGE_NOT_FOUND, HttpStatus.BAD_REQUEST);}
}

package com.itaxi.server.exception.favorite;

import com.itaxi.server.exception.Message;
import org.springframework.http.HttpStatus;

public class FavorBadTypeException extends FavoriteException{
    public FavorBadTypeException(){super(Message.FAVOR_BAD_TYPE, HttpStatus.BAD_REQUEST);}
}

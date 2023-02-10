package com.itaxi.server.exception.favorite;

import com.itaxi.server.exception.Message;
import org.springframework.http.HttpStatus;

public class FavorNotFoundException extends FavoriteException {
    public FavorNotFoundException(){
        super(Message.FAVOR_NOT_FOUND, HttpStatus.NOT_FOUND);
    }
}

package com.itaxi.server.exception.favorite;

import com.itaxi.server.exception.Message;
import org.springframework.http.HttpStatus;

public class FavorDuplicateException extends FavoriteException {
    public FavorDuplicateException(){
        super(Message.FAVOR_JOINER_DUPLICATED, HttpStatus.BAD_REQUEST);
    }
}

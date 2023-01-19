package com.itaxi.server.exception.favorite;

import com.itaxi.server.exception.Message;
import org.springframework.http.HttpStatus;

public class FavorNoAuthorityException extends FavoriteException{
    public FavorNoAuthorityException(){super(Message.FAVOR_NO_AUTHORITY_EXCEPTION, HttpStatus.BAD_REQUEST);}
}

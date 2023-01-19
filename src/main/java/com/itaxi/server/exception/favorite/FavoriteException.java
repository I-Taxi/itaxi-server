package com.itaxi.server.exception.favorite;

import com.itaxi.server.exception.ITaxiException;
import org.springframework.http.HttpStatus;

public class FavoriteException extends ITaxiException {
    public FavoriteException(String message, HttpStatus httpStatus){super(message, httpStatus);}
}

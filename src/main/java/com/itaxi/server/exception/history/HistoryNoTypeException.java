package com.itaxi.server.exception.history;

import com.itaxi.server.exception.Message;
import org.springframework.http.HttpStatus;

public class HistoryNoTypeException extends HistoryException{
    public HistoryNoTypeException() {super(Message.HISTORY_NO_TYPE_EXCEPTION, HttpStatus.BAD_REQUEST);}
}

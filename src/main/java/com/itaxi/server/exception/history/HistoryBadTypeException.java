package com.itaxi.server.exception.history;

import com.itaxi.server.exception.Message;
import org.springframework.http.HttpStatus;

public class HistoryBadTypeException extends HistoryException{
    public HistoryBadTypeException() {super(Message.HISTORY_BAD_TYPE_EXCEPTION, HttpStatus.BAD_REQUEST);}
}

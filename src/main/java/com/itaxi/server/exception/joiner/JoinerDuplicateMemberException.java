package com.itaxi.server.exception.joiner;

import com.itaxi.server.exception.Message;
import org.springframework.http.HttpStatus;

public class JoinerDuplicateMemberException extends JoinerException {
    public JoinerDuplicateMemberException() {
        super(Message.JOINER_DUPLICATE_MEMBER, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

package com.itaxi.server.post.application.dto.stopover;

import com.itaxi.server.post.domain.Joiner;
import com.itaxi.server.post.domain.Stopover;
import lombok.Getter;

@Getter
public class PostGetStopoverInfo {
    private final Long stopoverId;
    private final String stopoverName;

    public PostGetStopoverInfo(Stopover stopover) {
        this.stopoverId = stopover.getId();
        this.stopoverName = stopover.getPlace().getName();
    }
}

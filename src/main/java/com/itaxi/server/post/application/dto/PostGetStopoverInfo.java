package com.itaxi.server.post.application.dto;

import com.itaxi.server.post.domain.Stopover;
import lombok.Getter;

@Getter
public class PostGetStopoverInfo {
    private final Long placeId;
    private final String placeName;

    public PostGetStopoverInfo(Stopover stopover) {
        this.placeId = stopover.getId();
        this.placeName = stopover.getPlace().getName();
    }
}

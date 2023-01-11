package com.itaxi.server.post.application.dto.stopover;

import com.itaxi.server.post.domain.Stopover;
import lombok.Getter;

@Getter
public class StopoverInfo {
    private final Long placeId;
    private final String placeName;

    public StopoverInfo(Stopover stopover) {
        this.placeId = stopover.getPlace().getId();
        this.placeName = stopover.getPlace().getName();
    }
}

package com.itaxi.server.post.application.dto;

import com.itaxi.server.post.domain.Stopover;
import lombok.Getter;

@Getter
public class StopoverInfo {
    private final Long id;
    private final String name;

    public StopoverInfo(Stopover stopover) {
        this.id = stopover.getPlace().getId();
        this.name = stopover.getPlace().getName();
    }
}

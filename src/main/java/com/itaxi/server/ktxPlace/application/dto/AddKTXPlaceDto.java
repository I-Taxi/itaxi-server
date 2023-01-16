package com.itaxi.server.ktxPlace.application.dto;

import com.itaxi.server.ktxPlace.domain.KTXPlace;
import lombok.Getter;

@Getter
public class AddKTXPlaceDto {
    private String name;
    private Long cnt = 0L;
    private String uid;

    public KTXPlace toEntity() {
        return KTXPlace.builder().name(this.name).cnt(this.cnt).build();
    }
}

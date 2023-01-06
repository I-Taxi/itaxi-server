package com.itaxi.server.ktxPlace.application.dto;

import com.itaxi.server.ktxPlace.domain.KTXPlace;
import lombok.Getter;

@Getter
public class KTXResDto {
    private Long id;
    private String name;
    private Long cnt;

    public KTXResDto(KTXPlace ktxPlace) {
        this.id = ktxPlace.getId();
        this.name = ktxPlace.getName();
        this.cnt = ktxPlace.getCnt();
    }
}

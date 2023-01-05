package com.itaxi.server.bannerPlace.application.dto;

import com.itaxi.server.bannerPlace.domain.BANNERPlace;
import lombok.Getter;

@Getter
public class BANNERResDto {
    private Long id;
    private String name;
    private Long cnt;

    public BANNERResDto(BANNERPlace ktxPlace) {
        this.id = ktxPlace.getId();
        this.name = ktxPlace.getName();
        this.cnt = ktxPlace.getCnt();
    }
}

package com.itaxi.server.bannerPlace.application.dto;

import com.itaxi.server.bannerPlace.domain.BANNERPlace;
import lombok.Getter;

@Getter
public class BANNERResDto {
    private Long id;
    private String name;
    private Long cnt;

    public BANNERResDto(BANNERPlace bannerPlace) {
        this.id = bannerPlace.getId();
        this.name = bannerPlace.getName();
        this.cnt = bannerPlace.getCnt();
    }
}

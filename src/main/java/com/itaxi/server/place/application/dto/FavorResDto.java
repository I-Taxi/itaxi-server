package com.itaxi.server.place.application.dto;

import com.itaxi.server.place.domain.Place;
import lombok.Getter;

@Getter
public class FavorResDto {
    private String name;
    private Long cnt;
    private Long Id;

    public FavorResDto(Place place) {
        this.Id = place.getId();
        this.name = place.getName();
        this.cnt = place.getCnt();
    }
}

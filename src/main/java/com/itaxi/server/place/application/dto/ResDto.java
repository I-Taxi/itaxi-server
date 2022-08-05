package com.itaxi.server.place.application.dto;

import com.itaxi.server.place.domain.Place;
import lombok.Getter;

@Getter
public class ResDto {
    private String name;
    private Long cnt;

    public ResDto(Place place) {
        this.name = place.getName();
        this.cnt = place.getCnt();
    }
}
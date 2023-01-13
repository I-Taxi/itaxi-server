package com.itaxi.server.place.application.dto;

import com.itaxi.server.place.domain.Place;
import lombok.Getter;

@Getter
public class ResDto {
    private String name;
    private Long cnt;
    private int placeType;

    public ResDto(Place place) {
        this.name = place.getName();
        this.cnt = place.getCnt();
        this.placeType = place.getPlaceType();
    }
}
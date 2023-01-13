package com.itaxi.server.place.application.dto;

import com.itaxi.server.place.domain.Place;
import lombok.Getter;

@Getter
public class FindPlaceDto {
    private Long Id;
    private String name;
    private Long cnt;
    private int placeType;

    public FindPlaceDto(Place p){
        this.Id = p.getId();
        this.name = p.getName();
        this.cnt = p.getCnt();
        this.placeType = p.getPlaceType();
    }
}

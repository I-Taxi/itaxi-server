package com.itaxi.server.place.presentation.reponse;

import com.itaxi.server.place.domain.Place;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlaceFindResponse {
    private Long Id;
    private String name;
    private Long cnt;
    private int placeType;

    public PlaceFindResponse(Place p){
        this.Id = p.getId();
        this.name = p.getName();
        this.cnt = p.getCnt();
        this.placeType = p.getPlaceType();
    }


}


package com.itaxi.server.place.presentation.reponse;

import com.itaxi.server.place.application.dto.FindPlaceDto;
import com.itaxi.server.place.domain.Place;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlaceFindResponse {
    private Long Id;
    private String name;
    private Long cnt;
    private int placeType;
    private List<FindPlaceDto> contain;

    public PlaceFindResponse(Place p,List<Place> ps){
        this.Id = p.getId();
        this.name = p.getName();
        this.cnt = p.getCnt();
        this.placeType = p.getPlaceType();
        this.contain = new ArrayList<>();
        if(p.getPlaceType()==3){
            for(Place place: ps){
                FindPlaceDto dto = new FindPlaceDto(place);
                if(dto.getPlaceType()==0){
                    this.contain.add(dto);
                }
            }
        }
        if(p.getPlaceType()==4){
            for(Place place: ps){
                FindPlaceDto dto = new FindPlaceDto(place);
                if(dto.getPlaceType()==1){
                    this.contain.add(dto);
                }
            }

        }


    }
}

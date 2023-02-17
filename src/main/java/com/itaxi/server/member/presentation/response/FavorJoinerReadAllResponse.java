package com.itaxi.server.member.presentation.response;

import com.itaxi.server.place.application.dto.FavorResDto;
import com.itaxi.server.place.domain.Place;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FavorJoinerReadAllResponse implements Comparable<FavorJoinerReadAllResponse>{
    private Long id;
    private int favorType;
    private FavorResDto place;


    public FavorJoinerReadAllResponse(Place p, Long j,int f) {
        this.id = j;
        this.place = new FavorResDto(p);
        this.favorType = f;
    }

    @Override
    public int compareTo(FavorJoinerReadAllResponse favorJoinerReadAllResponse) {
        return this.place.getCnt().compareTo(favorJoinerReadAllResponse.getPlace().getCnt());
    }
}

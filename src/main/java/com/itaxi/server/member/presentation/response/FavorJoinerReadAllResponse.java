package com.itaxi.server.member.presentation.response;

import com.itaxi.server.member.domain.FavorJoiner;
import com.itaxi.server.place.application.dto.FavorResDto;
import com.itaxi.server.place.domain.Place;
import com.itaxi.server.post.application.dto.PostLog;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FavorJoinerReadAllResponse implements Comparable<FavorJoinerReadAllResponse>{
    private Long id;
    private FavorResDto place;


    public FavorJoinerReadAllResponse(Place p, Long j) {
        this.id = j;
        this.place = new FavorResDto(p);
    }

    @Override
    public int compareTo(FavorJoinerReadAllResponse favorJoinerReadAllResponse) {
        return this.place.getCnt().compareTo(favorJoinerReadAllResponse.getPlace().getCnt());
    }
}

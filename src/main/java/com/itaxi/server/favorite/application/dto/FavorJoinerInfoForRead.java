package com.itaxi.server.favorite.application.dto;

import com.itaxi.server.favorite.domain.FAVORJoiner;
import com.itaxi.server.member.domain.Member;
import com.itaxi.server.place.domain.Place;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class FavorJoinerInfoForRead {
    private final List<Long> joinerId;
    private final List<Place> places;

    public FavorJoinerInfoForRead(Member m,int favorType) {
        List<FAVORJoiner> FAVORJoiners = m.getFAVORJoiners();
        this.places = new ArrayList<>();
        this.joinerId = new ArrayList<>();
        if(favorType ==0){
            for (FAVORJoiner joiners : FAVORJoiners) {
                if(joiners.getPlace().getName().contains("전체")==false){
                    joinerId.add(joiners.getId());
                    places.add(joiners.getPlace());
                }
            }
        }
        else if(favorType==1){
            for (FAVORJoiner joiners : FAVORJoiners) {
                joinerId.add(joiners.getId());
                places.add(joiners.getPlace());
            }

        }

    }
}

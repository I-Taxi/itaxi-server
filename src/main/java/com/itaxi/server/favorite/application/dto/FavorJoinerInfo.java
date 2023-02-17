package com.itaxi.server.favorite.application.dto;

import com.itaxi.server.favorite.domain.FAVORJoiner;
import com.itaxi.server.member.domain.Member;
import com.itaxi.server.place.domain.Place;
import lombok.Getter;

import java.util.*;

@Getter
public class FavorJoinerInfo {
    private final List<FAVORJoiner> joiner;
    private final List<Place> places;

    public FavorJoinerInfo(Member m,int favorType) {
        List<FAVORJoiner> FAVORJoiners = m.getFAVORJoiners();
        this.places = new ArrayList<>();
        this.joiner = new ArrayList<>();
        for (FAVORJoiner joiners : FAVORJoiners) {
            if(joiners.getFavorType()==favorType){
                joiner.add(joiners);
                places.add(joiners.getPlace());
            }
        }

    }
}

package com.itaxi.server.favorite.application.dto;

import com.itaxi.server.favorite.domain.FAVORJoiner;
import com.itaxi.server.member.domain.Member;
import com.itaxi.server.place.domain.Place;
import lombok.Getter;

import java.util.*;

@Getter
public class FavorJoinerInfo {
    private final List<Long> joinerId;
    private final List<Place> places;

    public FavorJoinerInfo(Member m) {
        List<FAVORJoiner> FAVORJoiners = m.getFAVORJoiners();
        this.places = new ArrayList<>();
        this.joinerId = new ArrayList<>();
        for (FAVORJoiner joiners : FAVORJoiners) {
            joinerId.add(joiners.getId());
            places.add(joiners.getPlace());
        }

    }
}

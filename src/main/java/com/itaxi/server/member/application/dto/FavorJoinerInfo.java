package com.itaxi.server.member.application.dto;

import com.itaxi.server.member.domain.FavorJoiner;
import com.itaxi.server.member.domain.Member;
import com.itaxi.server.place.application.dto.ResDto;
import com.itaxi.server.place.domain.Place;
import lombok.Getter;

import java.util.*;

@Getter
public class FavorJoinerInfo {
    private final List<Long> joinerId;
    private final List<Place> places;

    public FavorJoinerInfo(Member m) {
        List<FavorJoiner> favorJoiners = m.getFavorJoiners();
        this.places = new ArrayList<>();
        this.joinerId = new ArrayList<>();
        for (FavorJoiner joiners : favorJoiners) {
            joinerId.add(joiners.getId());
            places.add(joiners.getPlace());
        }

    }
}

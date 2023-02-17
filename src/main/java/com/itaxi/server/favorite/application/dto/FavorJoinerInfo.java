package com.itaxi.server.favorite.application.dto;

import com.itaxi.server.favorite.domain.FAVORJoiner;
import com.itaxi.server.member.domain.Member;
import com.itaxi.server.place.domain.Place;
import lombok.Getter;

import java.util.*;

@Getter
public class FavorJoinerInfo {
    private final List<FAVORJoiner> joiner;

    public FavorJoinerInfo(Member m) {
        List<FAVORJoiner> FAVORJoiners = m.getFAVORJoiners();
        this.joiner = new ArrayList<>();
        for (FAVORJoiner joiners : FAVORJoiners) {
            joiner.add(joiners);
        }

    }
}

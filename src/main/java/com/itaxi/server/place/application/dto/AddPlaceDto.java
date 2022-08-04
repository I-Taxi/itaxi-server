package com.itaxi.server.place.application.dto;

import com.itaxi.server.place.domain.Place;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AddPlaceDto {
    private String name;
    private Long cnt = 0L;

    public Place toEntity() {
        return Place.builder()
                .name(this.name)
                .cnt(this.cnt)
                .build();
    }
}

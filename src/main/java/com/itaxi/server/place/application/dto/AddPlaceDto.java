package com.itaxi.server.place.application.dto;

import com.itaxi.server.place.domain.Place;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AddPlaceDto {
    @NotBlank(message = "place name은 필수 값 입니다.")
    private String name;
    private Long cnt = 0L;
    private int placetype;

    public Place toEntity() {
        return Place.builder()
                .name(this.name)
                .cnt(this.cnt)
                .placetype(this.placetype)
                .build();
    }
}

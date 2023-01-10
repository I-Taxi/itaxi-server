package com.itaxi.server.bannerPlace.application.dto;

import com.itaxi.server.bannerPlace.domain.BANNERPlace;
import com.itaxi.server.ktxPlace.domain.KTXPlace;
import lombok.Getter;

import javax.validation.constraints.NotBlank;


@Getter
public class AddBANNERPlaceDto {
    private String name;
    private Long cnt = 0L;

    public BANNERPlace toEntity() {
        return BANNERPlace.builder().name(this.name).cnt(this.cnt).build();
    }
}

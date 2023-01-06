package com.itaxi.server.bannerPlace.application.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class DeleteBANNERPlaceDto {
    private boolean deleted = true;

    @Builder
    public DeleteBANNERPlaceDto() {
        this.deleted = true;
    }
}

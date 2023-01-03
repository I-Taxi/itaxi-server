package com.itaxi.server.ktxPlace.application.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class DeleteKTXPlaceDto {
    private boolean deleted = true;

    @Builder
    public DeleteKTXPlaceDto() {
        this.deleted = true;
    }
}

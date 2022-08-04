package com.itaxi.server.place.application.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class DeletePlaceDto {
    private boolean deleted=true;

    @Builder
    public DeletePlaceDto() {
        this.deleted = true;
    }
}
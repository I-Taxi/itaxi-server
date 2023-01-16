package com.itaxi.server.ktxPlace.application.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UpdateKTXPlaceDto {
    private String uid;
    private String name;
}

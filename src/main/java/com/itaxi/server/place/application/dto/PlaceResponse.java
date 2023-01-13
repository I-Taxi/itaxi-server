package com.itaxi.server.place.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class PlaceResponse {
    private Long id;
    private String name;
    private Long cnt;
}

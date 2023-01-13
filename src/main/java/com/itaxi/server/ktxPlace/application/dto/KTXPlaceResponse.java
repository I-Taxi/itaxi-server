package com.itaxi.server.ktxPlace.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class KTXPlaceResponse {
    private Long id;
    private String name;
    private Long cnt;
}

package com.itaxi.server.favorite.presentation.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FavorReadRequest {
    private String uid;
    private int favorType;
}

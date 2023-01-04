package com.itaxi.server.banner.presentation.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BannerUpdateRequest {
    private String uid;
    private int weatherStatus;
    private Long depId;
    private Long desId;
}

package com.itaxi.server.banner.presentation.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class BannerCreateRequest {

    private String uid;
    private int weatherStatus;
    private Long depId;
    private Long desId;
    private LocalDateTime reportAt;
    private int bannerType;


}

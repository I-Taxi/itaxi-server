package com.itaxi.server.banner.presentation.reponse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BannerCreateResponse {
    private Long id;
    private String name;
    private String uid;
    private int weatherStatus;
    private Long departureId;
    private Long destinationId;
    private LocalDateTime reportAt;
    private int bannerType;
}

package com.itaxi.server.banner.application.dto;

import com.itaxi.server.banner.presentation.request.BannerCreateRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BannerCreateDto {

    private String uid;
    private int weatherStatus;
    private Long depId;
    private Long desId;
    private LocalDateTime reportAt;
    private int bannerType;

    public static BannerCreateDto from(BannerCreateRequest request) {
        return new BannerCreateDto(request.getUid(), request.getWeatherStatus(),request.getDepId(),request.getDesId(),request.getReportAt(), request.getBannerType());
    }
}


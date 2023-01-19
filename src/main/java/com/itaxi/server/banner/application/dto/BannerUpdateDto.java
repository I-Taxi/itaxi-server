package com.itaxi.server.banner.application.dto;

import com.itaxi.server.banner.presentation.request.BannerUpdateRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BannerUpdateDto {
    private int weatherStatus;
    private Long depId;
    private Long desId;

    private String uid;
    public static BannerUpdateDto from(BannerUpdateRequest request) {
        return new BannerUpdateDto( request.getWeatherStatus(),request.getDepId(),request.getDesId(), request.getUid());
    }
}

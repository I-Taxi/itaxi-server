package com.itaxi.server.banner.application.dto;

import com.itaxi.server.banner.presentation.request.BannerCreateRequest;
import com.itaxi.server.banner.presentation.request.BannerUpdateRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BannerUpdateDto {
    private String uid;
    private int weatherStatus;
    private Long depId;
    private Long desId;
    public static BannerUpdateDto from(BannerUpdateRequest request) {
        return new BannerUpdateDto(request.getUid(), request.getWeatherStatus(),request.getDepId(),request.getDesId());
    }
}

package com.itaxi.server.banner.application.dto;

import com.itaxi.server.banner.presentation.request.BannerDeleteRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BannerDeleteDto {
    private String uid;

    public static BannerDeleteDto from(BannerDeleteRequest request){
        return new BannerDeleteDto(request.getUid());
    }
}

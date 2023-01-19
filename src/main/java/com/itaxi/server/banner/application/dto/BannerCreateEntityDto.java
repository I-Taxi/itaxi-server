package com.itaxi.server.banner.application.dto;

import com.itaxi.server.place.domain.Place;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BannerCreateEntityDto {
    private String uid;
    private int weatherStatus;
    private Place depId;
    private Place desId;
    private LocalDateTime reportAt;
    private int bannerType;
}

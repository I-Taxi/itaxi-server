package com.itaxi.server.banner.presentation.reponse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BannerUpdateResponse {
    private int weatherStatus;
    private Long depId;
    private Long desId;
}

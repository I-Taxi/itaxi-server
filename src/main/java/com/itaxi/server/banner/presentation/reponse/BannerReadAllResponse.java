package com.itaxi.server.banner.presentation.reponse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BannerReadAllResponse {
    private Long id;
    private String name;
    private int weatherStatus;
    private Long departureId;
    private Long destinationId;
    private LocalDateTime reportedAt;


}

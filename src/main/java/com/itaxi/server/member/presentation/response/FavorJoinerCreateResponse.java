package com.itaxi.server.member.presentation.response;

import com.itaxi.server.place.domain.Place;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FavorJoinerCreateResponse {
    private Long id;
    private Place place;
    private LocalDateTime createdAt;
}

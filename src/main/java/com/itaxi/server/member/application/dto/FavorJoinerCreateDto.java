package com.itaxi.server.member.application.dto;

import com.itaxi.server.member.domain.Member;
import com.itaxi.server.place.domain.Place;
import com.itaxi.server.post.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FavorJoinerCreateDto {
    private String memberUid;
    private Long placeId;
}

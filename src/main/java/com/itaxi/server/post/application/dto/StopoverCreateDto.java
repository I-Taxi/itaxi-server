package com.itaxi.server.post.application.dto;

import com.itaxi.server.place.domain.Place;
import com.itaxi.server.post.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StopoverCreateDto {
    private Place place;
    private Post post;
}

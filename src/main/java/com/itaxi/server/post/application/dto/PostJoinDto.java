package com.itaxi.server.post.application.dto;

import com.itaxi.server.post.presentation.request.PostJoinRequest;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class PostJoinDto {
    private String uid;
    private int luggage;

    public static PostJoinDto from(PostJoinRequest request) {
        return new PostJoinDto(request.getUid(), request.getLuggage());
    }
}

package com.itaxi.server.post.application.dto;

import com.itaxi.server.post.presentation.request.PostJoinRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostJoinDto {
    private String uid;
    private int luggage;
    private boolean owner;

    public static PostJoinDto from(PostJoinRequest request, boolean owner) {
        return new PostJoinDto(request.getUid(), request.getLuggage(), owner);
    }
}

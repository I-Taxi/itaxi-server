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
    private int status;
    private int luggage;

    public static PostJoinDto from(PostJoinRequest request) {
        return new PostJoinDto(request.getUid(), request.getStatus(), request.getLuggage());
    }
}

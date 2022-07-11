package com.itaxi.server.post.presentation.request;

import com.itaxi.server.post.application.dto.PostJoinDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostJoinRequest {
    private String uid;
    private int luggage;
}

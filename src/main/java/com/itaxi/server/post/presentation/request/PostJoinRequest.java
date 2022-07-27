package com.itaxi.server.post.presentation.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostJoinRequest {
    private String uid;
    private int luggage;
}

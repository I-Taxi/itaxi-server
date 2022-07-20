package com.itaxi.server.post.application.dto;

import com.itaxi.server.member.domain.Member;
import com.itaxi.server.post.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class JoinerCreateDto {
    private Member member;
    private Post post;
    private int luggage;
    private boolean owner;
}

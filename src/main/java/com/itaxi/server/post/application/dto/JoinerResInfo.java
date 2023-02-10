package com.itaxi.server.post.application.dto;

import com.itaxi.server.post.domain.Joiner;
import lombok.Getter;

@Getter
public class JoinerResInfo {
    private final Long memberId;
    private final boolean owner;

    public JoinerResInfo(Joiner joiner) {
        this.memberId = joiner.getMember().getId();
        this.owner = joiner.isOwner();
    }
}

package com.itaxi.server.post.application.dto;

import com.itaxi.server.post.domain.Joiner;

import lombok.Getter;

@Getter
public class JoinerInfo {
    private final Long memberId;
    private final String memberName;
    private final String memberPhone;
    private final boolean owner;

    public JoinerInfo(Joiner joiner) {
        this.memberId = joiner.getMember().getId();
        this.memberName = joiner.getMember().getName();
        this.memberPhone = joiner.getMember().getPhone();
        this.owner = joiner.isOwner();
    }
}
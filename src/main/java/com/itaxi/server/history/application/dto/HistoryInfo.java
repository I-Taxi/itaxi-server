package com.itaxi.server.history.application.dto;

import com.itaxi.server.ktx.domain.KTXJoiner;
import com.itaxi.server.post.domain.Joiner;
import lombok.Getter;

@Getter
public class HistoryInfo {
    private final Long memberId;
    private final String memberName;
    private final String memberPhone;
    private final boolean owner;

    public HistoryInfo(KTXJoiner ktxJoiner) {
        this.memberId = ktxJoiner.getMember().getId();
        this.memberName = ktxJoiner.getMember().getName();
        this.memberPhone = ktxJoiner.getMember().getPhone();
        this.owner = ktxJoiner.isOwner();
    }
    public HistoryInfo(Joiner joiner) {
        this.memberId = joiner.getMember().getId();
        this.memberName = joiner.getMember().getName();
        this.memberPhone = joiner.getMember().getPhone();
        this.owner = joiner.isOwner();
    }
}

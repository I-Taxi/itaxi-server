package com.itaxi.server.ktx.application.dto;

import com.itaxi.server.ktx.domain.KTXJoiner;
import lombok.Getter;

@Getter
public class KTXJoinerInfo {
    private final Long memberId;
    private final String memberName;
    private final String memberPhone;
    private final boolean owner;

    public KTXJoinerInfo(KTXJoiner ktxJoiner) {
        this.memberId = ktxJoiner.getMember().getId();
        this.memberName = ktxJoiner.getMember().getName();
        this.memberPhone = ktxJoiner.getMember().getPhone();
        this.owner = ktxJoiner.isOwner();
    }
}

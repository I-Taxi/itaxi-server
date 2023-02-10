package com.itaxi.server.ktx.application.dto;

import com.itaxi.server.ktx.domain.KTXJoiner;
import lombok.Getter;

@Getter
public class KTXJoinerResInfo {
    private final Long memberId;
    private final boolean owner;

    public KTXJoinerResInfo(KTXJoiner ktxJoiner) {
        this.memberId = ktxJoiner.getMember().getId();
        this.owner = ktxJoiner.isOwner();
    }
}

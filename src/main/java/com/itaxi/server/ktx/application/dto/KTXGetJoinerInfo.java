package com.itaxi.server.ktx.application.dto;

import com.itaxi.server.ktx.domain.KTXJoiner;
import lombok.Getter;

@Getter
public class KTXGetJoinerInfo {
    private final String uid;
    private final Long memberId;
    private final  String memberName;
    private final String memberPhone;
    private final boolean owner;

    public KTXGetJoinerInfo(KTXJoiner ktxJoiner) {
        this.uid = ktxJoiner.getMember().getUid();
        this.memberId = ktxJoiner.getMember().getId();
        this.memberName = ktxJoiner.getMember().getName();
        this.memberPhone = ktxJoiner.getMember().getPhone();
        this.owner = ktxJoiner.isOwner();
    }
}

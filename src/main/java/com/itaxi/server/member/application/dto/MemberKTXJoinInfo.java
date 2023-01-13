package com.itaxi.server.member.application.dto;

import com.itaxi.server.ktx.domain.KTX;
import com.itaxi.server.ktx.domain.KTXJoiner;
import com.itaxi.server.member.domain.Member;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class MemberKTXJoinInfo {
    private final List<KTX> ktxes;

    public MemberKTXJoinInfo(Member m) {
        List<KTXJoiner> joiners = m.getKtxJoiners();
        this.ktxes = new ArrayList<>();
        for (KTXJoiner ktxJoiner : joiners) {
            if (ktxJoiner.getStatus() == 1) {
                ktxes.add(ktxJoiner.getKtx());
            }
        }
    }
}

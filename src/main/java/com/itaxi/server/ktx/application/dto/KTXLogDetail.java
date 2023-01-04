package com.itaxi.server.ktx.application.dto;

import com.itaxi.server.ktx.domain.KTX;
import com.itaxi.server.ktx.domain.KTXJoiner;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class KTXLogDetail extends KTXLog {
    private final List<KTXJoinerInfo> joiners;

    public KTXLogDetail(KTX ktx) {
        super(ktx);
        joiners = new ArrayList<>();
        for (KTXJoiner ktxJoiner : ktx.getJoiners()) {
            if (ktxJoiner.getStatus() == 1) {
                joiners.add(new KTXJoinerInfo(ktxJoiner));
            }
        }
    }
}

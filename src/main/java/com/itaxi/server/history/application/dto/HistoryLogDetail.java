package com.itaxi.server.history.application.dto;

import com.itaxi.server.ktx.domain.KTX;
import com.itaxi.server.ktx.domain.KTXJoiner;
import com.itaxi.server.post.domain.Joiner;
import com.itaxi.server.post.domain.Post;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
@Getter
public class HistoryLogDetail extends HistoryLog{
    private final List<HistoryInfo> joiners;

    public HistoryLogDetail(Post p) {
        super(p);
        joiners = new ArrayList<>();
        for(Joiner joiner : p.getJoiners()) {
            if (joiner.getStatus() == 1)
                joiners.add(new HistoryInfo(joiner));
        }
    }

    public HistoryLogDetail(KTX ktx) {
        super(ktx);
        joiners = new ArrayList<>();
        for (KTXJoiner ktxJoiner : ktx.getJoiners()) {
            if (ktxJoiner.getStatus() == 1) {
                joiners.add(new HistoryInfo(ktxJoiner));
            }
        }
    }
}

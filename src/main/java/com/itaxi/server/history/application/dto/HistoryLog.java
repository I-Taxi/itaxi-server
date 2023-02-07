package com.itaxi.server.history.application.dto;

import com.itaxi.server.ktx.domain.KTX;
import com.itaxi.server.ktx.domain.KTXJoiner;
import com.itaxi.server.place.application.dto.PlaceResponse;
import com.itaxi.server.post.application.dto.StopoverInfo;
import com.itaxi.server.post.domain.Joiner;
import com.itaxi.server.post.domain.Post;
import com.itaxi.server.post.domain.Stopover;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class HistoryLog implements Comparable<HistoryLog>{

    private final Long id;
    private final PlaceResponse departure;
    private final PlaceResponse destination;
    private final List<StopoverInfo> stopovers;
    private final LocalDateTime deptTime;
    private final int capacity;
    private final int status;
    private final Integer postType;
    private final int participantNum;

    public HistoryLog(Post p) {
        this.id = p.getId();
        this.departure = new PlaceResponse(p.getDeparture().getId(), p.getDeparture().getName(), p.getDeparture().getCnt());
        this.destination = new PlaceResponse(p.getDestination().getId(), p.getDestination().getName(), p.getDestination().getCnt());
        this.deptTime = p.getDeptTime();
        this.capacity = p.getCapacity();
        this.status = p.getStatus();
        this.postType = p.getPostType();

        int tmp = 0;
        for(Joiner joiner : p.getJoiners()) {
            if(joiner.getStatus() == 1)
                tmp += 1;
        }
        this.participantNum = tmp;

        stopovers = new ArrayList<>();
        for (Stopover stopover : p.getStopovers()) {
            stopovers.add(new StopoverInfo(stopover));
        }
    }
    public HistoryLog(KTX ktx) {
        this.id = ktx.getId();
        this.departure = new PlaceResponse(ktx.getDeparture().getId(), ktx.getDeparture().getName(), ktx.getDeparture().getCnt());
        this.destination = new PlaceResponse(ktx.getDestination().getId(), ktx.getDestination().getName(), ktx.getDestination().getCnt());
        this.deptTime = ktx.getDeptTime();
        this.capacity = ktx.getCapacity();
        this.status = ktx.getStatus();
        this.postType = 3;
        this.stopovers = null;
        int tmp = 0;
        for (KTXJoiner ktxJoiner : ktx.getJoiners()) {
            if (ktxJoiner.getStatus() == 1) tmp += 1;
        }
        this.participantNum = tmp;
    }

    @Override
    public int compareTo(HistoryLog historyLog) {
        return this.deptTime.compareTo(historyLog.getDeptTime());
    }
}

package com.itaxi.server.ktx.application.dto;

import com.itaxi.server.ktxPlace.domain.KTXPlace;
import com.itaxi.server.ktx.domain.KTX;
import com.itaxi.server.ktx.domain.KTXJoiner;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class KTXGetResDto {
    private Long id;
    private KTXPlace departure;
    private KTXPlace destination;
    private LocalDateTime deptTime;
    private int capacity;
    private int participantNum;
    private int status;

    private List<KTXGetJoinerInfo> joiners;

    @Builder public KTXGetResDto(KTX ktx) {
        this.id = ktx.getId();
        this.departure = ktx.getDeparture();
        this.destination = ktx.getDestination();
        this.deptTime = ktx.getDeptTime();
        this.capacity = ktx.getCapacity();
        this.participantNum = ktx.getJoiners().size();
        this.status = ktx.getStatus();
        joiners = new ArrayList<KTXGetJoinerInfo>();

        for (KTXJoiner ktxJoiner : ktx.getJoiners()) {
            if (ktxJoiner.getStatus() == 1) {
                joiners.add(new KTXGetJoinerInfo(ktxJoiner));
            }
        }
    }
}

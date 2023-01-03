package com.itaxi.server.ktx.application.dto;

import com.itaxi.server.ktxPlace.domain.KTXPlace;
import com.itaxi.server.ktx.domain.KTX;
import com.itaxi.server.ktx.domain.KTXJoiner;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class KTXLog implements Comparable<KTXLog> {
    private Long id;
    private KTXPlace departure;
    private KTXPlace destination;
    private LocalDateTime deptTime;
    private int capacity;
    private int participantNum;
    private int status;

    public KTXLog(KTX ktx) {
        this.id = ktx.getId();
        this.departure = ktx.getDeparture();
        this.destination = ktx.getDestination();
        this.deptTime = ktx.getDeptTime();
        this.capacity = ktx.getCapacity();
        this.status = ktx.getStatus();
        int tmp = 0;
        for (KTXJoiner ktxJoiner : ktx.getJoiners()) {
            if (ktxJoiner.getStatus() == 1) tmp += 1;
        }
        this.participantNum = tmp;
    }

    @Override
    public int compareTo(KTXLog ktxLog) {
        return this.deptTime.compareTo(ktxLog.getDeptTime());
    }
}

package com.itaxi.server.ktx.application.dto;

import com.itaxi.server.ktxPlace.domain.KTXPlace;
import com.itaxi.server.ktx.domain.KTX;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class KTXResDto {
    private Long id;
    private KTXPlace departure;
    private KTXPlace destination;
    private LocalDateTime deptTime;
    private int capacity;
    private int participantNum;
    private int status;

    @Builder
    public KTXResDto(KTX ktx) {
        this.id = ktx.getId();
        this.departure = ktx.getDeparture();
        this.destination = ktx.getDestination();
        this.deptTime = ktx.getDeptTime();
        this.capacity = ktx.getCapacity();
        this.participantNum = ktx.getJoiners().size();
        this.status = ktx.getStatus();
    }
}

package com.itaxi.server.ktx.application.dto;

import com.itaxi.server.ktx.domain.KTXJoiner;
import com.itaxi.server.ktxPlace.application.dto.KTXPlaceResponse;
import com.itaxi.server.ktx.domain.KTX;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class KTXGetResDto {
    private Long id;
    private KTXPlaceResponse departure;
    private KTXPlaceResponse destination;
    private LocalDateTime deptTime;
    private int capacity;
    private int participantNum;
    private int status;
    private final List<KTXJoinerInfo> joiners;
    private int sale;


    @Builder public KTXGetResDto(KTX ktx) {
        this.id = ktx.getId();
        this.departure = new KTXPlaceResponse(ktx.getDeparture().getId(), ktx.getDeparture().getName(), ktx.getDeparture().getCnt());
        this.destination = new KTXPlaceResponse(ktx.getDestination().getId(), ktx.getDestination().getName(), ktx.getDestination().getCnt());
        this.deptTime = ktx.getDeptTime();
        this.capacity = ktx.getCapacity();
        this.participantNum = ktx.getJoiners().size();
        this.status = ktx.getStatus();
        joiners = new ArrayList<KTXJoinerInfo>();
        for (KTXJoiner ktxJoiner : ktx.getJoiners()){
            joiners.add(new KTXJoinerInfo(ktxJoiner));
        }
        this.sale = ktx.getSale();
        }
    }


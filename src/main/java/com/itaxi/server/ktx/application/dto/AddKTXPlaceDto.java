package com.itaxi.server.ktx.application.dto;

import com.itaxi.server.ktxPlace.domain.KTXPlace;
import com.itaxi.server.ktx.domain.KTX;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class AddKTXPlaceDto {
    private KTXPlace departure;
    private KTXPlace destination;
    private LocalDateTime deptTime;
    private int capacity;
    private int status;
    private int sale;

    @Builder
    public AddKTXPlaceDto(AddKTXDto req, KTXPlace departure, KTXPlace destination) {
        this.departure = departure;
        this.destination = destination;
        this.deptTime = req.getDeptTime();
        this.capacity = req.getCapacity();
        this.status = 1;
        this.sale = req.getSale();
    }

    public KTX toEntity() {
        return KTX.builder()
                .departure(this.departure)
                .destination(this.destination)
                .deptTime(this.deptTime)
                .capacity(this.capacity)
                .status(this.status)
                .sale(this.sale)
                .build();
    }
}

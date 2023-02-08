package com.itaxi.server.ktx.application.dto;

import com.itaxi.server.ktxPlace.application.dto.KTXPlaceResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class KTXInfoResponse {
    private Long id;
    private KTXPlaceResponse departure;
    private KTXPlaceResponse destination;
    private LocalDateTime deptTime;
    private int capacity;
    private int status;
    private int sale;
    private List<KTXJoinerInfo> joiners;
}

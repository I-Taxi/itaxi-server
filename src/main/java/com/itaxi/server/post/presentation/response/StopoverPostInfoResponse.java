package com.itaxi.server.post.presentation.response;

import com.itaxi.server.place.application.PlaceResponse;
import com.itaxi.server.post.application.dto.JoinerInfo;
import com.itaxi.server.post.application.dto.stopover.StopoverInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StopoverPostInfoResponse {
    private Long id;
    private PlaceResponse departure;
    private PlaceResponse destination;
    private List<StopoverInfo> stopovers;
    private LocalDateTime deptTime;
    private int capacity;
    private int status;
    private int postType;
    private List<JoinerInfo> joiners;
}

package com.itaxi.server.post.presentation.response;

import com.itaxi.server.place.application.PlaceResponse;
import com.itaxi.server.post.domain.dto.JoinerInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostInfoResponse {
    private Long id;
    private PlaceResponse departure;
    private PlaceResponse destination;
    private LocalDateTime deptTime;
    private int capacity;
    private int status;
    private List<JoinerInfo> joiners;
}
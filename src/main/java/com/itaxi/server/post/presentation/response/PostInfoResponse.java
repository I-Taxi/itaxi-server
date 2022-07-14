package com.itaxi.server.post.presentation.response;

import com.itaxi.server.place.domain.Place;
import com.itaxi.server.post.domain.Joiner;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
public class PostInfoResponse {
    private Long id;
    private Place departure;
    private Place destination;
    private LocalDateTime deptTime;
    private int capacity;
    private int status;
    private List<Joiner> joiners;
}

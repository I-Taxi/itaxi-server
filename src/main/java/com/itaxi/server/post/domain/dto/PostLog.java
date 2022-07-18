package com.itaxi.server.post.domain.dto;

import com.itaxi.server.place.domain.Place;
import com.itaxi.server.post.domain.Post;

import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class PostLog {
    private final Long id;
    private final Place departure;
    private final Place destination;
    private final LocalDateTime deptTime;
    private final int capacity;
    private final int status;
//    private final int participantNum;

    public PostLog(Post p) {
        this.id = p.getId();
        this.departure = p.getDeparture();
        this.destination = p.getDestination();
        this.deptTime = p.getDeptTime();
        this.capacity = p.getCapacity();
        this.status = p.getStatus();
//        this.participantNum = p.getJoiners().size();
    }
}

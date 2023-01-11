package com.itaxi.server.post.application.dto.stopover;

import com.itaxi.server.place.domain.Place;
import com.itaxi.server.post.domain.Post;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class StopoverResDto {
    private Long id;
    private Place departure;
    private Place destination;
    private LocalDateTime deptTime;
    private int capacity;
    private int participantNum;
    private int status;
    private Integer postType;


    @Builder
    public StopoverResDto(Post post) {
        this.id = post.getId();
        this.departure = post.getDeparture();
        this.destination = post.getDestination();
        this.deptTime = post.getDeptTime();
        this.capacity = post.getCapacity();
        this.participantNum = post.getJoiners().size();
        this.status = post.getStatus();
        this.postType = post.getPostType();
    }
}

package com.itaxi.server.post.application.dto.stopover;

import com.itaxi.server.place.domain.Place;
import com.itaxi.server.post.domain.Post;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class AddStopoverPostPlaceDto {
    private Place departure;
    private Place destination;
    private LocalDateTime deptTime;
    private int capacity;
    private int status;
    private Integer postType;


    @Builder
    public AddStopoverPostPlaceDto(AddStopoverPostDto req, Place departure, Place destination) {
        this.departure = departure;
        this.destination = destination;
        this.postType = req.getPostType();
        this.deptTime = req.getDeptTime();
        this.capacity = req.getCapacity();
        this.status = 1;
    }

    public Post toEntity() {
        return Post.builder()
                .departure(this.departure)
                .destination(this.destination)
                .deptTime(this.deptTime)
                .capacity(this.capacity)
                .status(this.status)
                .postType(this.postType)
                .build();
    }
}

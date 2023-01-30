package com.itaxi.server.post.application.dto;

import com.itaxi.server.place.application.dto.PlaceResponse;
import com.itaxi.server.post.domain.Joiner;
import com.itaxi.server.post.domain.Post;
import com.itaxi.server.post.domain.Stopover;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class PostGetResDto {
    private Long id;
    private PlaceResponse departure;
    private PlaceResponse destination;
    private LocalDateTime deptTime;
    private int capacity;
    private int participantNum;
    private int status;
    private Integer postType;
    private List<PostGetStopoverInfo> stopovers;


    @Builder
    public PostGetResDto(Post post) {
        this.id = post.getId();
        this.departure = new PlaceResponse(post.getDeparture().getId(), post.getDeparture().getName(), post.getDeparture().getCnt());
        this.destination = new PlaceResponse(post.getDestination().getId(), post.getDestination().getName(), post.getDestination().getCnt());
        this.deptTime = post.getDeptTime();
        this.capacity = post.getCapacity();
        this.participantNum = post.getJoiners().size();
        this.status = post.getStatus();
        this.postType = post.getPostType();

        stopovers = new ArrayList<PostGetStopoverInfo>();
        for(Stopover stopover : post.getStopovers()) {
            stopovers.add(new PostGetStopoverInfo(stopover));
        }
    }
}

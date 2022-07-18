package com.itaxi.server.post.presentation.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.itaxi.server.place.domain.Place;
import com.itaxi.server.post.domain.Joiner;
import com.itaxi.server.post.domain.Post;
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
    @JsonIgnoreProperties({"hibernateLazyInitializer"})
    private Place departure;
    @JsonIgnoreProperties({"hibernateLazyInitializer"})
    private Place destination;
    private LocalDateTime deptTime;
    private int capacity;
    private int status;
    private List<Joiner> joiners;


    public PostInfoResponse(Post post, List<Joiner> joiners) {
        this.id = post.getId();
        this.destination = post.getDestination();
        this.deptTime = post.getDeptTime();
        this.capacity = post.getCapacity();
        this.status = post.getStatus();
        this.joiners.addAll(joiners);
    }
}
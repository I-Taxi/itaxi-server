package com.itaxi.server.post.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.itaxi.server.place.application.PlaceResponse;
import com.itaxi.server.place.domain.Place;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

import com.itaxi.server.common.BaseEntity;

import com.itaxi.server.post.domain.dto.JoinerInfo;
import com.itaxi.server.post.presentation.response.PostInfoResponse;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;

@Where(clause = "deleted=false")
@Entity
@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicUpdate
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Post extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private Place departure;

    @ManyToOne(fetch = FetchType.EAGER)
    private Place destination;

    private LocalDateTime deptTime;

    private int capacity;

    private int status;
    
    private int postType;

    private int luggage;

    @OneToMany(mappedBy = "post")
    private List<Joiner> joiners = new ArrayList<>();

    @Builder
    public Post(Place departure, Place destination, LocalDateTime deptTime, int capacity, int status, int luggage, Integer postType) {
        this.departure = departure;
        this.destination = destination;
        this.deptTime = deptTime;
        this.capacity = capacity;
        this.status = status;
        this.luggage = luggage;
        this.postType = postType;
    }

    public PostInfoResponse toPostInfoResponse() {
        PlaceResponse deptResponse = new PlaceResponse(departure.getId(), departure.getName(), departure.getCnt());
        PlaceResponse destResponse = new PlaceResponse(destination.getId(), destination.getName(), destination.getCnt());

        List<JoinerInfo> joinerResponse = new ArrayList<>();
        for(Joiner joiner : joiners) {
            joinerResponse.add(new JoinerInfo(joiner));
        }

        return new PostInfoResponse(id, deptResponse, destResponse, deptTime, capacity, status, joinerResponse);
    }
}




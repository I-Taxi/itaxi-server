package com.itaxi.server.post.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.itaxi.server.place.application.dto.PlaceResponse;
import com.itaxi.server.place.domain.Place;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.itaxi.server.common.BaseEntity;

import com.itaxi.server.post.application.dto.JoinerInfo;
import com.itaxi.server.post.application.dto.StopoverInfo;
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

    @NotNull
    private LocalDateTime deptTime;

    @NotNull
    private int capacity;

    private int status;

    @NotNull
    private Integer postType;

    @OneToMany(mappedBy = "post")
    private List<Joiner> joiners = new ArrayList<>();

    @OneToMany(mappedBy = "post")
    private List<Stopover> stopovers = new ArrayList<>();

    @Builder
    public Post(Place departure, Place destination, LocalDateTime deptTime, int capacity, int status, Integer postType) {
        this.departure = departure;
        this.destination = destination;
        this.deptTime = deptTime;
        this.capacity = capacity;
        this.status = status;
        this.postType = postType;
    }

    public PostInfoResponse toPostInfoResponse() {
        PlaceResponse deptResponse = new PlaceResponse(departure.getId(), departure.getName(), departure.getCnt());
        PlaceResponse destResponse = new PlaceResponse(destination.getId(), destination.getName(), destination.getCnt());

        List<JoinerInfo> joinerResponse = new ArrayList<>();
        for(Joiner joiner : joiners) {
            joinerResponse.add(new JoinerInfo(joiner));
        }

        List<StopoverInfo> stopoverResponse = new ArrayList<>();
        for(Stopover stopover : stopovers) {
            stopoverResponse.add(new StopoverInfo(stopover));
        }

        return new PostInfoResponse(id, deptResponse, destResponse, deptTime, capacity, status, postType, joinerResponse, stopoverResponse);
    }
}




package com.itaxi.server.post.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.itaxi.server.place.domain.Place;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

import com.itaxi.server.common.BaseEntity;

import com.itaxi.server.post.presentation.response.PostInfoResponse;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;

@Where(clause = "deleted=false")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicUpdate
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Post extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer"})
    private Place departure;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer"})
    private Place destination;

    private LocalDateTime deptTime;

    private int capacity;

    private int status;

    private boolean deleted = false;

    // TODO : 이거 지우는 방법 모색
//    @OneToMany(mappedBy = "post")
//    private List<Joiner> joiners = new ArrayList<>();

    @Builder
    public Post(Place departure, Place destination, LocalDateTime deptTime, int capacity, int status) {
        this.departure = departure;
        this.destination = destination;
        this.deptTime = deptTime;
        this.capacity = capacity;
        this.status = status;
    }
}




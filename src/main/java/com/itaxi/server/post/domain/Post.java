package com.itaxi.server.post.domain;
import com.itaxi.server.place.domain.Place;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

import com.itaxi.server.common.BaseEntity;

import lombok.Getter;
import org.hibernate.annotations.Where;

@Where(clause = "deleted=false")
@Entity
@Getter
public class Post extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Place departure;

    @ManyToOne(fetch = FetchType.LAZY)
    private Place destination;

    private LocalDateTime deptTime;

    private int capacity;

    private int status;

    private boolean deleted = false;

    @OneToMany(mappedBy = "post")
    private List<Joiner> joiners = new ArrayList<>();

    // TODO : delete test
    public Post(Place departure, Place destination, LocalDateTime deptTime, int capacity, int status, boolean deleted) {
        this.departure = departure;
        this.destination = destination;
        this.deptTime = deptTime;
        this.capacity = capacity;
        this.status = status;
        this.deleted = deleted;
    }
}

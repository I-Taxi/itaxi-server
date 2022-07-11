package com.itaxi.server.post.domain;
import com.itaxi.server.place.domain.Place;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

import com.itaxi.server.common.BaseEntity;

import lombok.Getter;

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

    @OneToMany(mappedBy = "post")
    private List<Joiner> joiners = new ArrayList<>();
}

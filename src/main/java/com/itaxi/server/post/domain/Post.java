package com.itaxi.server.post.domain;

import java.time.LocalDateTime;
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

    private LocalDateTime dstTime;

    private int capacity;

    private int status;

    @OneToMany(mappedBy = "post")
    private List<Joiner> joiners;
}

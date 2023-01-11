package com.itaxi.server.post.domain;


import javax.persistence.*;

import com.itaxi.server.common.BaseEntity;
import com.itaxi.server.place.domain.Place;
import com.itaxi.server.post.application.dto.StopoverCreateDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Stopover extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Place place;

    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;

    public Stopover(StopoverCreateDto stopoverCreateDto) {
        this.place = stopoverCreateDto.getPlace();
        this.post = stopoverCreateDto.getPost();
    }
}

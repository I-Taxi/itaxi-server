package com.itaxi.server.place.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import com.itaxi.server.common.BaseEntity;
import com.itaxi.server.favorite.domain.FAVORJoiner;
import com.itaxi.server.place.application.dto.UpdatePlaceDto;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;

import java.util.ArrayList;
import java.util.List;

@Where(clause = "deleted=false")
@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicUpdate
public class Place extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Column(unique = true)
    private String name;
    private Long cnt;
    @OneToMany(mappedBy = "place")
    private List<FAVORJoiner> FAVORJoiners = new ArrayList<>();
    private int placeType;


    @Builder
    public Place(String name, Long cnt,int placeType) {
        this.name = name;
        this.cnt = cnt;
        this.placeType = placeType;
    }

    public void updatePlace(UpdatePlaceDto dto) {
        this.name = dto.getName();
        this.placeType =dto.getPlaceType();
    }
}

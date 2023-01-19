package com.itaxi.server.place.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import com.itaxi.server.common.BaseEntity;
import com.itaxi.server.favorite.domain.FavorJoiner;
import com.itaxi.server.place.application.dto.UpdatePlaceDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;

import java.util.ArrayList;
import java.util.List;

@Where(clause = "deleted=false")
@Entity
@Getter
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
    private List<FavorJoiner> favorJoiners= new ArrayList<>();
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

package com.itaxi.server.place.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import com.itaxi.server.common.BaseEntity;
import com.itaxi.server.place.application.PlaceDto;
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
public class Place extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Long cnt;

    @Builder
    public Place(String name, Long cnt) {
        this.name = name;
        this.cnt = cnt;
    }

    public void updatePlace(PlaceDto.UpdatePlaceReq dto) {
        this.name = dto.getName();
    }
}

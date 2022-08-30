package com.itaxi.server.place.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import com.itaxi.server.common.BaseEntity;
import com.itaxi.server.place.application.dto.UpdatePlaceDto;
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
    @NotBlank
    private String name;
    private Long cnt;

    @Builder
    public Place(String name, Long cnt) {
        this.name = name;
        this.cnt = cnt;
    }

    public void updatePlace(UpdatePlaceDto dto) {
        this.name = dto.getName();
    }
}

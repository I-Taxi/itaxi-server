package com.itaxi.server.favorite.domain;


import com.itaxi.server.common.BaseEntity;
import com.itaxi.server.favorite.application.dto.FavorJoinerSaveDto;
import com.itaxi.server.member.domain.Member;
import com.itaxi.server.place.domain.Place;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;

//@Where(clause = "deleted=false")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FAVORJoiner extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int favorType;
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;
    @ManyToOne(fetch = FetchType.LAZY)
    private Place place;




    public FAVORJoiner(FavorJoinerSaveDto favorJoinerSaveDto) {
        this.member = favorJoinerSaveDto.getMember();
        this.place = favorJoinerSaveDto.getPlace();
        this.favorType = favorJoinerSaveDto.getFavorType();
    }
}

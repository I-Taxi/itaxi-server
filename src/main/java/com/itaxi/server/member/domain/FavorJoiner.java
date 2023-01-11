package com.itaxi.server.member.domain;


import com.itaxi.server.common.BaseEntity;
import com.itaxi.server.member.application.dto.FavorJoinerCreateDto;
import com.itaxi.server.place.domain.Place;
import com.itaxi.server.post.application.dto.JoinerCreateDto;
import com.itaxi.server.post.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Where(clause = "deleted=false")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FavorJoiner extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;
    @ManyToOne(fetch = FetchType.LAZY)
    private Place place;
    private boolean favorite;

    public FavorJoiner(FavorJoinerCreateDto favorjoinerCreateDto) {
        this.member = favorjoinerCreateDto.getMember();
        this.place = favorjoinerCreateDto.getPlace();
        this.favorite = favorjoinerCreateDto.isFavorite();
    }
}

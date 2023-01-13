package com.itaxi.server.member.domain;


import com.itaxi.server.common.BaseEntity;
import com.itaxi.server.member.application.dto.FavorJoinerCreateDto;
import com.itaxi.server.member.application.dto.FavorJoinerSaveDto;
import com.itaxi.server.place.domain.Place;
import com.itaxi.server.post.application.dto.JoinerCreateDto;
import com.itaxi.server.post.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

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




    public FavorJoiner(FavorJoinerSaveDto favorJoinerSaveDto) {
        this.member = favorJoinerSaveDto.getMember();
        this.place = favorJoinerSaveDto.getPlace();
    }
}

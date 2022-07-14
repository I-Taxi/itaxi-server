package com.itaxi.server.post.domain;

import javax.persistence.*;

import com.itaxi.server.common.BaseEntity;
import com.itaxi.server.member.domain.Member;

import com.itaxi.server.post.application.dto.JoinerCreateDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

@Where(clause = "deleted=false")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Joiner extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;

    private int status;

    private int luggage;

    private boolean owner = false;

    public Joiner(JoinerCreateDto joinerCreateDto) {
        this.member = joinerCreateDto.getMember();
        this.post = joinerCreateDto.getPost();
        this.status = joinerCreateDto.getStatus();
        this.luggage = joinerCreateDto.getLuggage();
    }
}

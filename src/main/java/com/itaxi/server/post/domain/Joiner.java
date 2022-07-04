package com.itaxi.server.post.domain;

import javax.persistence.*;

import com.itaxi.server.common.BaseEntity;
import com.itaxi.server.member.domain.Member;

import lombok.Getter;

@Entity
@Getter
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

    private boolean owner;

}

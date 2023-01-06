package com.itaxi.server.ktx.domain;

import com.itaxi.server.common.BaseEntity;
import com.itaxi.server.ktx.application.dto.KTXJoinerCreateDto;
import com.itaxi.server.member.domain.Member;
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
public class KTXJoiner extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    private KTX ktx;

    private int status;

    private boolean owner;

    public KTXJoiner(KTXJoinerCreateDto ktxJoinerCreateDto) {
        this.member = ktxJoinerCreateDto.getMember();
        this.ktx = ktxJoinerCreateDto.getKtx();
        this.status = 1;
        this.owner = ktxJoinerCreateDto.isOwner();
    }
}

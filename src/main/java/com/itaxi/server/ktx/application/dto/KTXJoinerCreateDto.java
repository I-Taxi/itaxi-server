package com.itaxi.server.ktx.application.dto;

import com.itaxi.server.ktx.domain.KTX;
import com.itaxi.server.member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class KTXJoinerCreateDto {
    private Member member;
    private KTX ktx;
    private boolean owner;
}

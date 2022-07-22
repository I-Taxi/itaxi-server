package com.itaxi.server.member.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MemberUpdateRequestDTO {
    private String uid;
    private String phone;
    private String bank;
    private String bankAddress;
}

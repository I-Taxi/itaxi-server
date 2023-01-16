package com.itaxi.server.member.application.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MemberCreateRequestDTO {
    private String uid;
    private String email;
    private String phone;
    private String name;
    private String bank;
    private String bankAddress;
    private String bankOwner;
}
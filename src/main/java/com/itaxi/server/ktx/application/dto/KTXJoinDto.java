package com.itaxi.server.ktx.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class KTXJoinDto {
    private String uid;
    private boolean owner;

    public static KTXJoinDto from(KTXJoinRequest request, boolean owner) {
        return new KTXJoinDto(request.getUid(), owner);
    }
}

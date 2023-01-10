package com.itaxi.server.ktx.application.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AddKTXDto {
    @NotNull
    private String uid;
    @NotNull
    private Long depId;
    @NotNull
    private Long dstId;
    @NotNull
    private LocalDateTime deptTime;
    @NotNull
    private int capacity;
}

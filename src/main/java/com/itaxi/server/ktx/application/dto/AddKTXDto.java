package com.itaxi.server.ktx.application.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AddKTXDto {
    private String uid;
    private Long depId;
    private Long dstId;
    private LocalDateTime deptTime;
    @NotBlank
    private int capacity;
    private int status = 0;
}

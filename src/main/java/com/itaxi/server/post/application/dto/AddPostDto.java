package com.itaxi.server.post.application.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AddPostDto {
    private String uid;
    private Long depId;
    private Long dstId;
    private LocalDateTime deptTime;
    @NotBlank
    private int capacity;
    private int status = 0;
    @NotBlank
    private int luggage;
    private Integer postType;
}
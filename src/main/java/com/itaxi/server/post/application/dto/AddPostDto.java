package com.itaxi.server.post.application.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AddPostDto {
    private String uid;
    private Long depId;
    private Long dstId;
    private List<Long> stopoverIds;
    private LocalDateTime deptTime;
    @NotBlank
    private int capacity;
    private Integer postType;
}
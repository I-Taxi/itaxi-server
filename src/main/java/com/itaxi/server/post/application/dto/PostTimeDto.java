package com.itaxi.server.post.application.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostTimeDto {
    private String uid;
    private LocalDateTime deptTime;
    private Integer postType;
}

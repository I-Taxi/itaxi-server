package com.itaxi.server.post.application.dto.stopover;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AddStopoverPostDto {
    private String uid;
    private Long depId;
    private Long dstId;
    private List<Long> stopoverIds;
    private LocalDateTime deptTime;
    @NotBlank
    private int capacity;
    private int status = 0;
    private Integer postType;
}

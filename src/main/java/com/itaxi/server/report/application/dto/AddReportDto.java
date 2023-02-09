package com.itaxi.server.report.application.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AddReportDto {

    private String uid;
    private Long reportedMemberId;
    private LocalDateTime dateTime;
    private String title;
    private String content;
}

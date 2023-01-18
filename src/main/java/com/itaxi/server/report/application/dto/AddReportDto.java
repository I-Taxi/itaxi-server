package com.itaxi.server.report.application.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AddReportDto {
    private String writerUid;
    private String reportedUid;
    private LocalDateTime dateTime;
    private String title;
    private String content;
}

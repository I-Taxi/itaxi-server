package com.itaxi.server.report.application.dto;

import com.itaxi.server.report.domain.Report;
import com.itaxi.server.report.presentation.response.MemberResponse;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ReportGetResDto {
    private Long id;
    private MemberResponse writer;
    private MemberResponse reportedMember;
    private LocalDateTime dateTime;
    private String content;
    private String title;

    @Builder
    public ReportGetResDto(Report report) {
        this.id = report.getId();
        this.writer = new MemberResponse(report.getWriter().getId(), report.getWriter().getName());
        this.reportedMember = new MemberResponse(report.getReportedMember().getId(), report.getReportedMember().getName());
        this.dateTime = report.getDate();
        this.content = report.getContent();
        this.title = report.getTitle();
    }
}

package com.itaxi.server.report.presentation.response;

import com.itaxi.server.report.domain.Report;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReportResponse {
    private Long id;
    private MemberResponse writer;
    private MemberResponse reportedMember;
    private String title;
    private String content;

    public ReportResponse(Report report) {
        this.id = report.getId();
        this.writer = new MemberResponse(report.getWriter().getId(), report.getWriter().getName());
        this.reportedMember = new MemberResponse(report.getReportedMember().getId(), report.getReportedMember().getName());
        this.title = report.getTitle();
        this.content = report.getContent();
    }
}

package com.itaxi.server.report.application.dto;

import com.itaxi.server.member.domain.Member;
import com.itaxi.server.report.domain.Report;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class AddReportMemberDto {
    private Member writer;
    private Member reportedMember;
    private LocalDateTime date;
    private String content;
    private String title;

    @Builder
    public AddReportMemberDto(AddReportDto dto, Member writer, Member reportedMember) {
        this.writer = writer;
        this.reportedMember = reportedMember;
        this.date = dto.getDateTime();
        this.content = dto.getContent();
        this.title = dto.getTitle();
    }

    public Report toEntity() {
        return Report.builder()
                .writer(this.writer)
                .reportedMember(this.reportedMember)
                .date(this.date)
                .content(this.content)
                .title(this.title)
                .build();
    }
}

package com.itaxi.server.report.presentation.response;

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
    private LocalDateTime date;
    private String title;
    private String content;
}

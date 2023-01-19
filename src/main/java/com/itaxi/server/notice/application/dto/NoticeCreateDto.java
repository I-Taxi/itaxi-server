package com.itaxi.server.notice.application.dto;



import com.itaxi.server.notice.presentation.request.NoticeCreateRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class NoticeCreateDto {
    private String title;
    private String content;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private int noticeType;

    public static NoticeCreateDto from(NoticeCreateRequest request) {
        return new NoticeCreateDto(request.getTitle(), request.getContent(),request.getStartTime(),request.getEndTime(),request.getNoticeType());
    }
}

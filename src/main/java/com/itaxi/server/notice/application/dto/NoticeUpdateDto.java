package com.itaxi.server.notice.application.dto;

import com.itaxi.server.notice.presentation.request.NoticeUpdateRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class NoticeUpdateDto {
    private String title;
    private String content;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public static NoticeUpdateDto from(NoticeUpdateRequest request) {
        return new NoticeUpdateDto(request.getTitle(), request.getContent(),request.getStartTime(),request.getEndTime());
    }
}

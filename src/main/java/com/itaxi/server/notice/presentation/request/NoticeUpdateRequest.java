package com.itaxi.server.notice.presentation.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class NoticeUpdateRequest {
    private String title;
    private String content;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private int bannerType;
}

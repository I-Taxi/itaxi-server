package com.itaxi.server.notice.presentation.request;

import com.itaxi.server.notice.application.dto.NoticeCreateDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NoticeCreateRequest {
    private Long noticeId;
    private String title;
    private String content;
    private Long viewCnt;
}

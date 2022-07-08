package com.itaxi.server.notice.application.dto;

import com.itaxi.server.notice.domain.Notice;
import com.itaxi.server.notice.presentation.request.NoticeCreateRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class NoticeCreateDto {
    private Long noticeId;
    private String title;
    private String content;
    private Long viewCnt;

    public static NoticeCreateDto from(NoticeCreateRequest request) {
        return new NoticeCreateDto(request.getNoticeId(), request.getTitle(), request.getContent(), request.getViewCnt());
    }
}

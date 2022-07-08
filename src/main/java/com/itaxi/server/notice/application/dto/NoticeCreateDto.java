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
    private String title;
    private String content;

    public static NoticeCreateDto from(NoticeCreateRequest request) {
        return new NoticeCreateDto(request.getTitle(), request.getContent());
    }
}

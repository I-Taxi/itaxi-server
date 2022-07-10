package com.itaxi.server.notice.application.dto;

import com.itaxi.server.notice.presentation.request.NoticeUpdateRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class NoticeUpdateDto {
    private String title;
    private String content;

    public static NoticeUpdateDto from(NoticeUpdateRequest request) {
        return new NoticeUpdateDto(request.getTitle(), request.getContent());
    }
}

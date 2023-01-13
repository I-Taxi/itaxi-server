package com.itaxi.server.notice.presentation.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class NoticeCreateRequest {
    private String title;
    private String content;
    private String uid;
}

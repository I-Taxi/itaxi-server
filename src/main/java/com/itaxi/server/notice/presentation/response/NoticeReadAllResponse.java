package com.itaxi.server.notice.presentation.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NoticeReadAllResponse {
    private Long id;
    private String title;
    private String content;
    private Long viewCnt;
    private LocalDateTime createAt;
}

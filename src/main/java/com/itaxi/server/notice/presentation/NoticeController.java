package com.itaxi.server.notice.presentation;

import com.itaxi.server.notice.application.NoticeService;

import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class NoticeController {
    private final NoticeService noticeService;
}

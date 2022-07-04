package com.itaxi.server.notice.application;

import com.itaxi.server.notice.domain.repository.NoticeRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class NoticeService {
    private final NoticeRepository noticeRepository;
}

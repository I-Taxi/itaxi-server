package com.itaxi.server.notice.application;

import com.itaxi.server.notice.application.dto.NoticeCreateDto;
import com.itaxi.server.notice.domain.repository.NoticeRepository;
import com.itaxi.server.notice.domain.Notice;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class NoticeService {
    private final NoticeRepository noticeRepository;

    public Long create(NoticeCreateDto noticeCreateDto) {
        Notice savedNotice = noticeRepository.save(new Notice(noticeCreateDto));

        return savedNotice.getId();
    }
}

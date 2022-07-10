package com.itaxi.server.notice.application;

import com.itaxi.server.exception.notice.NoticeException;
import com.itaxi.server.exception.notice.NoticeNotFoundException;
import com.itaxi.server.notice.application.dto.NoticeCreateDto;
import com.itaxi.server.notice.application.dto.NoticeUpdateDto;
import com.itaxi.server.notice.domain.repository.NoticeRepository;
import com.itaxi.server.notice.domain.Notice;

import com.itaxi.server.notice.presentation.response.NoticeReadResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class NoticeService {
    private final NoticeRepository noticeRepository;

    public Long createNotice(NoticeCreateDto noticeCreateDto) {
        Notice savedNotice = noticeRepository.save(new Notice(noticeCreateDto));

        return savedNotice.getId();
    }

    public String updateNotice(Long noticeId, NoticeUpdateDto noticeUpdateDto) {
        try {
            Optional<Notice> notice = noticeRepository.findById(noticeId);
            if (notice.isPresent()) {
                Notice noticeInfo = notice.get();
                noticeInfo.setTitle(noticeUpdateDto.getTitle());
                noticeInfo.setContent(noticeUpdateDto.getContent());
                noticeRepository.save(noticeInfo);
            } else {
                throw new NoticeNotFoundException(HttpStatus.INTERNAL_SERVER_ERROR);
            }

            return "Success";
        } catch (NoticeNotFoundException e) {
            e.printStackTrace();

            return "Failed";
        }

    }

    public String deleteNotice(Long noticeId) {
        try {
            Optional<Notice> notice = noticeRepository.findById(noticeId);
            if (notice.isPresent()) {
                Notice noticeInfo = notice.get();
                noticeInfo.setDeleted(true);
                noticeRepository.save(noticeInfo);
            } else {
                throw new NoticeNotFoundException(HttpStatus.INTERNAL_SERVER_ERROR);
            }

            return "Success";
        } catch (NoticeNotFoundException e) {
            e.printStackTrace();

            return "Failed";
        }
    }

    public NoticeReadResponse readNotice(Long noticeId) {
        NoticeReadResponse response = null;
        try {
            Optional<Notice> notice = noticeRepository.findById(noticeId);
            if (notice.isPresent()) {
                Notice noticeInfo = notice.get();
                noticeInfo.setDeleted(true);
                noticeRepository.save(noticeInfo);

                response = new NoticeReadResponse(noticeInfo.getTitle(), noticeInfo.getContent(), noticeInfo.getViewCnt(), noticeInfo.getCreatedAt());
            } else {
                throw new NoticeNotFoundException(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (NoticeNotFoundException e) {
            e.printStackTrace();
        }

        return response;
    }
}

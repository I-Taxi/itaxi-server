package com.itaxi.server.notice.application;

import com.itaxi.server.exception.notice.NoticeDeletedException;
import com.itaxi.server.exception.notice.NoticeException;
import com.itaxi.server.exception.notice.NoticeNotFoundException;
import com.itaxi.server.notice.application.dto.NoticeCreateDto;
import com.itaxi.server.notice.application.dto.NoticeUpdateDto;
import com.itaxi.server.notice.domain.repository.NoticeRepository;
import com.itaxi.server.notice.domain.Notice;

import com.itaxi.server.notice.presentation.response.NoticeReadAllResponse;
import com.itaxi.server.notice.presentation.response.NoticeReadResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class NoticeService {
    private final NoticeRepository noticeRepository;

    public Long createNotice(NoticeCreateDto noticeCreateDto) {
        Notice savedNotice = noticeRepository.save(new Notice(noticeCreateDto));

        return savedNotice.getId();
    }


    public NoticeReadResponse readNotice(Long noticeId) {
        NoticeReadResponse response = null;
        try {
            Optional<Notice> notice = noticeRepository.findById(noticeId);
            if (notice.isPresent()) {
                Notice noticeInfo = notice.get();
                if (!noticeInfo.isDeleted()) {
                    response = new NoticeReadResponse(noticeInfo.getTitle(), noticeInfo.getContent(), noticeInfo.getViewCnt(), noticeInfo.getCreatedAt());
                } else {
                    throw new NoticeDeletedException(HttpStatus.INTERNAL_SERVER_ERROR);
                }
            } else {
                throw new NoticeNotFoundException(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (NoticeNotFoundException | NoticeDeletedException e) {
            e.printStackTrace();
        }

        return response;
    }


    public List<NoticeReadAllResponse> readAllNotices() {
        List<NoticeReadAllResponse> result = new ArrayList<>();
        for (Notice notice : noticeRepository.findAll()) {
            if (notice != null) {
                if (!notice.isDeleted()) {
                    result.add(new NoticeReadAllResponse(notice.getId(), notice.getTitle(), notice.getContent(), notice.getViewCnt(), notice.getCreatedAt()));
                }
            }
        }

        return result;
    }

    public String updateNotice(Long noticeId, NoticeUpdateDto noticeUpdateDto) {
        try {
            Optional<Notice> notice = noticeRepository.findById(noticeId);
            if (notice.isPresent()) {
                Notice noticeInfo = notice.get();
                if (!noticeInfo.isDeleted()) {
                    noticeInfo.setTitle(noticeUpdateDto.getTitle());
                    noticeInfo.setContent(noticeUpdateDto.getContent());
                    noticeRepository.save(noticeInfo);
                } else {
                    throw new NoticeDeletedException(HttpStatus.INTERNAL_SERVER_ERROR);
                }
            } else {
                throw new NoticeNotFoundException(HttpStatus.INTERNAL_SERVER_ERROR);
            }

            return "Success";
        } catch (NoticeNotFoundException | NoticeDeletedException e) {
            e.printStackTrace();

            return "Failed";
        }

    }

    public String deleteNotice(Long noticeId) {
        try {
            Optional<Notice> notice = noticeRepository.findById(noticeId);
            if (notice.isPresent()) {
                Notice noticeInfo = notice.get();
                // 이미 지워졌는지 확인
                if (!noticeInfo.isDeleted()) {
                    noticeInfo.setDeleted(true);
                    noticeRepository.save(noticeInfo);
                } else {
                    throw new NoticeDeletedException(HttpStatus.INTERNAL_SERVER_ERROR);
                }
            } else {
                throw new NoticeNotFoundException(HttpStatus.INTERNAL_SERVER_ERROR);
            }

            return "Success";
        } catch (NoticeNotFoundException | NoticeDeletedException e) {
            e.printStackTrace();

            return "Failed";
        }
    }
}

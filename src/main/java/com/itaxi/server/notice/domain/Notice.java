package com.itaxi.server.notice.domain;

import javax.persistence.*;

import com.itaxi.server.common.BaseEntity;
import com.itaxi.server.exception.notice.NoticeContentEmptyException;
import com.itaxi.server.exception.notice.NoticeException;
import com.itaxi.server.exception.notice.NoticeTitleEmptyException;
import com.itaxi.server.notice.application.dto.NoticeCreateDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class Notice extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    private Long viewCnt;

    public Notice(NoticeCreateDto noticeCreateDto) {
        String title = noticeCreateDto.getTitle();
        String content = noticeCreateDto.getContent();

        if (title == null || title.trim().isEmpty()) {
            throw new NoticeTitleEmptyException();
        }
        this.title = title;

        if (content == null || content.trim().isEmpty()) {
            throw new NoticeContentEmptyException();
        }
        this.content = content;
        this.viewCnt = (long)0;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.title = content;
    }

    public void setViewCnt(Long viewCnt) {
        this.viewCnt = viewCnt;
    }
}

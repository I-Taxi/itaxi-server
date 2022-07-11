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
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Notice extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    private long viewCnt = 0;

    private boolean deleted = false;

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
    }
}

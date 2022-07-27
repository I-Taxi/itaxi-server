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
import org.hibernate.annotations.Where;

@Where(clause = "deleted=false")
@Entity
@Getter
@Setter
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

    public Notice(NoticeCreateDto noticeCreateDto) {
        this.title = noticeCreateDto.getTitle();
        this.content = noticeCreateDto.getContent();
        this.setDeleted(false);
        checkTitleAndContent(title, content);
    }

    private void checkTitleAndContent(String title, String content) {
        if (title == null || title.trim().isEmpty()) {
            throw new NoticeTitleEmptyException();
        }

        if (content == null || content.trim().isEmpty()) {
            throw new NoticeContentEmptyException();
        }
    }
}

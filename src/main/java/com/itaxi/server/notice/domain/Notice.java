package com.itaxi.server.notice.domain;

import javax.persistence.*;

import com.itaxi.server.common.BaseEntity;
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

    public Notice(String title, String content) {
        this.title = title;
        this.content = content;
        this.viewCnt = (long)0;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.title = content;
    }

    public void setViewCnt(String viewCnt) {
        this.title = viewCnt;
    }
}

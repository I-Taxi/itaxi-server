package com.itaxi.server.report.domain;

import java.time.LocalDateTime;
import javax.persistence.*;

import com.itaxi.server.common.BaseEntity;
import com.itaxi.server.member.domain.Member;

import lombok.Getter;

@Entity
@Getter
public class Report extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member writer;

    private LocalDateTime date;

    private String content;

    private String title;

    private String deviceType;

    private String osVersion;

    private int status;
}

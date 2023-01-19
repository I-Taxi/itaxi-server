package com.itaxi.server.report.domain;

import java.time.LocalDateTime;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.itaxi.server.common.BaseEntity;
import com.itaxi.server.member.domain.Member;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;

@Where(clause = "deleted=false")
@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicUpdate
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Report extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member writer;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member reportedMember;

    @NotNull
    private LocalDateTime date;

    @NotNull
    private String content;

    @NotNull
    private String title;

    @Builder
    public Report(Member writer, Member reportedMember, LocalDateTime date, String content, String title) {
        this.writer = writer;
        this.reportedMember = reportedMember;
        this.date = date;
        this.content = content;
        this.title = title;
    }
}

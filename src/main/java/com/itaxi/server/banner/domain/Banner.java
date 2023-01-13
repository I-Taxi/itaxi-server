package com.itaxi.server.banner.domain;

import com.itaxi.server.banner.application.dto.BannerCreateDto;
import com.itaxi.server.bannerPlace.domain.BANNERPlace;
import com.itaxi.server.common.BaseEntity;
import com.itaxi.server.exception.banner.BannerUidEmptyException;
import com.itaxi.server.exception.notice.NoticeContentEmptyException;
import com.itaxi.server.exception.notice.NoticeTitleEmptyException;
import com.itaxi.server.member.domain.repository.MemberRepository;
import com.itaxi.server.place.domain.Place;
import com.itaxi.server.post.domain.Joiner;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Where(clause = "deleted=false")
@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@DynamicUpdate


public class Banner extends BaseEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String uid;
    @Column(nullable = false)
    private int weatherStatus;

    @Column(nullable = false)
    private Long departureId;
    @Column(nullable = false)
    private Long destinationId;

    @Column(nullable = false)
    private LocalDateTime reportAt;
    @Column(nullable = false)
    private int bannerType;

    @OneToMany(mappedBy = "banner")
    private List<Place> places = new ArrayList<>();


    public Banner(BannerCreateDto bannerCreateDto){
        this.uid = bannerCreateDto.getUid();
        this.weatherStatus = bannerCreateDto.getWeatherStatus();
        this.departureId = bannerCreateDto.getDepId();
        this.destinationId = bannerCreateDto.getDesId();
        this.reportAt = bannerCreateDto.getReportAt();
        this.bannerType = bannerCreateDto.getBannerType();
        this.setDeleted(false);
        checkUid(uid);
    }

    private void checkUid(String uid) {
        if ((uid == null || uid.trim().isEmpty())) {
            throw new BannerUidEmptyException();
        }
    }
}

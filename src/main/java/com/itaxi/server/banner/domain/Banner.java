package com.itaxi.server.banner.domain;

import com.itaxi.server.banner.application.dto.BannerCreateEntityDto;
import com.itaxi.server.common.BaseEntity;
import com.itaxi.server.exception.banner.BannerUidEmptyException;
import com.itaxi.server.place.domain.Place;
import lombok.*;
import org.hibernate.annotations.*;
import javax.persistence.*;
import javax.persistence.Entity;
import java.time.LocalDateTime;



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
    @ManyToOne(fetch = FetchType.EAGER)
    private Place departureId;
    @ManyToOne(fetch = FetchType.EAGER)
    private Place destinationId;
    @Column(nullable = false)
    private LocalDateTime reportAt;
    @Column(nullable = false)
    private int bannerType;


    public Banner(BannerCreateEntityDto bannerCreateEntityDto){
        this.uid = bannerCreateEntityDto.getUid();
        this.weatherStatus = bannerCreateEntityDto.getWeatherStatus();
        this.departureId = bannerCreateEntityDto.getDepId();
        this.destinationId = bannerCreateEntityDto.getDesId();
        this.reportAt = bannerCreateEntityDto.getReportAt();
        this.bannerType = bannerCreateEntityDto.getBannerType();
        this.setDeleted(false);
        checkUid(uid);
    }

    private void checkUid(String uid) {
        if ((uid == null || uid.trim().isEmpty())) {
            throw new BannerUidEmptyException();
        }
    }
}

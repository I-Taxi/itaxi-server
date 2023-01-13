package com.itaxi.server.bannerPlace.domain;

import com.itaxi.server.banner.domain.Banner;
import com.itaxi.server.bannerPlace.application.dto.UpdateBANNERPlaceDto;
import com.itaxi.server.common.BaseEntity;
import com.itaxi.server.ktxPlace.application.dto.UpdateKTXPlaceDto;
import com.itaxi.server.post.domain.Post;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;

import javax.persistence.*;


@Where(clause = "deleted=false")
@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicUpdate
public class BANNERPlace  extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private Long cnt;
//    @ManyToOne(fetch = FetchType.LAZY)
//    private Banner banner;

    @Builder
    public BANNERPlace(String name, Long cnt) {
        this.name = name;
        this.cnt = cnt;
    }

    public void updateBANNERPlace(UpdateBANNERPlaceDto dto) {
        this.name = dto.getName();
    }
}

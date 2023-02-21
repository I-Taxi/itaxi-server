package com.itaxi.server.advertisement.domain;

import com.itaxi.server.advertisement.application.dto.AdCreateDto;
import com.itaxi.server.common.BaseEntity;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.nio.file.FileStore;

@Where(clause = "deleted=false")
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@DynamicUpdate
public class Advertisement extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String path;
    private String url;
    @Column(unique = true)
    private String imgName;
    private String imgType;
    private String name;

    public Advertisement(AdCreateDto dto){
        this.path = dto.getPath();
        this.imgName = dto.getImgName();
        this.imgType = dto.getImgType();
        this.url = dto.getUrl();
        this.name = dto.getName();
    }
}

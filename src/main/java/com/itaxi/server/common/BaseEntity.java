package com.itaxi.server.common;

import java.time.LocalDateTime;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import com.itaxi.server.ktxPlace.application.dto.DeleteKTXPlaceDto;
import com.itaxi.server.place.application.dto.DeletePlaceDto;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@MappedSuperclass
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class BaseEntity {

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updateAt;

    private boolean deleted;

    public void deletePlace(DeletePlaceDto dto) {
        this.deleted = dto.isDeleted();
    }

    public void deleteKTXPlace(DeleteKTXPlaceDto dto) {
        this.deleted = dto.isDeleted();
    }
}

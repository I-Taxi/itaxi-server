package com.itaxi.server.bannerPlace.domain.repository;

import com.itaxi.server.bannerPlace.domain.BANNERPlace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface BANNERPlaceRepository extends JpaRepository<BANNERPlace, Long> {
    @Query("SELECT p FROM BANNERPlace p WHERE p.deleted = false ORDER BY p.cnt DESC NULLS LAST")
    Iterable<BANNERPlace> findByDeleted();

}

package com.itaxi.server.ktxPlace.domain.repository;

import com.itaxi.server.ktxPlace.domain.KTXPlace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface KTXPlaceRepository extends JpaRepository<KTXPlace, Long> {
    @Query("SELECT p FROM KTXPlace p WHERE p.deleted = false ORDER BY p.cnt DESC NULLS LAST")
    Iterable<KTXPlace> findByDeleted();

    @Modifying(clearAutomatically = true)
    @Query("UPDATE KTXPlace p SET p.cnt = p.cnt + 1 WHERE p.id = id")
    int updateView(Long id);
}

package com.itaxi.server.place.domain.repository;

import com.itaxi.server.place.domain.Place;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Long> {
    @Query("SELECT p FROM Place p WHERE  p.deleted = false order by p.cnt desc NULLS LAST")
    Iterable<Place> findByDeleted();

    @Modifying(clearAutomatically = true)
    @Query("update Place p set p.cnt = p.cnt + 1 where p.id = :id")
    int updateView(Long id);
}

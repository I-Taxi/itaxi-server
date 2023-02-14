package com.itaxi.server.place.domain.repository;

import com.itaxi.server.place.domain.Place;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Long> {
    @Query("SELECT p FROM Place p WHERE  p.deleted = false order by p.cnt desc NULLS LAST")
    Iterable<Place> findByDeleted();

    List<Place> findAllByPlaceType(Integer placeType);
}

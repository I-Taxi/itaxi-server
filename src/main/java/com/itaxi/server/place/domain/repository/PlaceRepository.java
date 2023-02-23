package com.itaxi.server.place.domain.repository;

import com.itaxi.server.place.domain.Place;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Long> {
    List<Place> findAllByPlaceType(Integer placeType);
}

package com.itaxi.server.ktxPlace.domain.repository;

import com.itaxi.server.ktxPlace.domain.KTXPlace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface KTXPlaceRepository extends JpaRepository<KTXPlace, Long> {
    Optional<KTXPlace> findByName(String name);
}

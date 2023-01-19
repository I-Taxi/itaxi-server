package com.itaxi.server.favorite.domain.repository;

import com.itaxi.server.favorite.domain.FAVORJoiner;
import com.itaxi.server.member.domain.Member;
import com.itaxi.server.place.domain.Place;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FavorJoinerRepository extends JpaRepository<FAVORJoiner, Long> {
    Optional<FAVORJoiner> findJoinerByPlaceAndMember(Place place, Member member);
    List<FAVORJoiner> findAllByMember(Member member);
}

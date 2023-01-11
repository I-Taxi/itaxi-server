package com.itaxi.server.member.domain.repository;

import com.itaxi.server.member.domain.Member;
import com.itaxi.server.place.domain.Place;
import com.itaxi.server.post.domain.Joiner;
import com.itaxi.server.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FavorJoinerRepository extends JpaRepository<Joiner, Long> {
    Optional<Joiner> findJoinerByPlaceAndMember(Place place, Member member);
    List<Joiner> findJoinersByPlace(Place place);
}

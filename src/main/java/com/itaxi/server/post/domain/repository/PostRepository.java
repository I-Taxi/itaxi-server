package com.itaxi.server.post.domain.repository;

import java.time.LocalDateTime;
import java.util.List;
import com.itaxi.server.post.domain.Post;
import com.itaxi.server.place.domain.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PostRepository extends JpaRepository<Post, Long> {

    //List<Person> findByLastname(String lastname);
    Iterable<Post> findAllByDeparture(Place departure);
    Iterable<Post> findByDestination(Place destination);
    Iterable<Post> findByDepartureId(long departureId);

    List<Post> findAllByDepartureAndDestinationAndDeptTime(Place departure, Place destination, LocalDateTime deptTime);
    List<Post> findAllByDepartureAndDestination(Place departure, Place destination);
}

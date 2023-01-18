package com.itaxi.server.post.domain.repository;

import java.time.LocalDateTime;
import java.util.List;
import com.itaxi.server.post.domain.Post;

import com.itaxi.server.place.domain.Place;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
    //List<Person> findByLastname(String lastname);
    Iterable<Post> findAllByDeparture(Place departure);
    Iterable<Post> findByDestination(Place destination);
    Iterable<Post> findByDepartureId(long departureId);
    List<Post> findByDeleted(boolean deleted);

    //List<Post> findAllByDepartureAndDestinationAndDeptTimeBetween(Place departure, Place destination, LocalDateTime deptTime1, LocalDateTime deptTime2);
    /* depid + dstid + deptTime */
    List<Post> findAllByDepartureAndDestinationAndDeptTimeBetweenOrderByDeptTime(Place departure, Place destination, LocalDateTime deptTime1, LocalDateTime deptTime2);
    /* type + depid + dstid + deptTime */
    List<Post> findAllByPostTypeAndDepartureAndDestinationAndDeptTimeBetweenOrderByDeptTime(Integer postType, Place departure, Place destination, LocalDateTime startDateTime, LocalDateTime endDateTime);
    /* deptTime */
    List<Post> findAllByDeptTimeBetweenOrderByDeptTime(LocalDateTime deptTime1, LocalDateTime deptTime2);
    /* type + deptTime */
    List<Post> findAllByPostTypeAndDeptTimeBetweenOrderByDeptTime(Integer postType, LocalDateTime deptTime1, LocalDateTime deptTime2);
    /* depid + deptTime */
    List<Post> findAllByDepartureAndDeptTimeBetweenOrderByDeptTime(Place departure, LocalDateTime startDateTime, LocalDateTime endDateTime);
    /* type + depid + deptTime */
    List<Post> findAllByPostTypeAndDepartureAndDeptTimeBetweenOrderByDeptTime(Integer postType, Place departure, LocalDateTime startDateTime, LocalDateTime endDateTime);
    /* dstid + deptTime */
    List<Post> findAllByDestinationAndDeptTimeBetweenOrderByDeptTime(Place departure, LocalDateTime startDateTime, LocalDateTime endDateTime);
    /* type + dstid + deptTime */
    List<Post> findAllByPostTypeAndDestinationAndDeptTimeBetweenOrderByDeptTime(Integer postType, Place departure, LocalDateTime startDateTime, LocalDateTime endDateTime);

}

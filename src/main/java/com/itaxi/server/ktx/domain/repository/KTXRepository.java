package com.itaxi.server.ktx.domain.repository;

import com.itaxi.server.ktxPlace.domain.KTXPlace;
import com.itaxi.server.ktx.domain.KTX;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface KTXRepository extends JpaRepository<KTX, Long> {
    List<KTX> findByDeleted(boolean deleted);

    /* depid + dstid + deptTime */
    List<KTX> findAllByDepartureAndDestinationAndDeptTimeBetweenOrderByDeptTime(KTXPlace departure, KTXPlace destination, LocalDateTime deptTime1, LocalDateTime deptTime2);
    /* deptTime */
    List<KTX> findAllByDeptTimeBetweenOrderByDeptTime(LocalDateTime deptTime1, LocalDateTime deptTime2);
   /* depid + deptTime */
    List<KTX> findAllByDepartureAndDeptTimeBetweenOrderByDeptTime(KTXPlace departure, LocalDateTime startDateTime, LocalDateTime endDateTime);
    /* dstid + deptTime */
    List<KTX> findAllByDestinationAndDeptTimeBetweenOrderByDeptTime(KTXPlace departure, LocalDateTime startDateTime, LocalDateTime endDateTime);
}

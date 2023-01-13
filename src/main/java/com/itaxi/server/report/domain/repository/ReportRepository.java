package com.itaxi.server.report.domain.repository;

import com.itaxi.server.report.domain.Report;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReportRepository extends JpaRepository<Report, Long> {
    List<Report> findAllByWriterId(Long id);
}

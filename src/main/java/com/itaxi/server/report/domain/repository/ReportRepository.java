package com.itaxi.server.report.domain.repository;

import com.itaxi.server.report.domain.Report;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report, Long> {
}

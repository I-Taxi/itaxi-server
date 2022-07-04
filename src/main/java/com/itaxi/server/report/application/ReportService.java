package com.itaxi.server.report.application;

import com.itaxi.server.report.domain.repository.ReportRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final ReportRepository reportRepository;

}

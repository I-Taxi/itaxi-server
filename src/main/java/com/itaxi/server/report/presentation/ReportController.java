package com.itaxi.server.report.presentation;

import com.itaxi.server.report.application.ReportService;

import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ReportController {
    private final ReportService reportService;
}

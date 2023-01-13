package com.itaxi.server.report.presentation;

import com.itaxi.server.docs.ApiDoc;
import com.itaxi.server.member.application.dto.MemberUidDTO;
import com.itaxi.server.report.application.ReportService;

import com.itaxi.server.report.application.dto.AddReportDto;
import com.itaxi.server.report.application.dto.ReportGetResDto;
import com.itaxi.server.report.application.dto.UpdateReportDto;
import com.itaxi.server.report.presentation.response.ReportResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/report")
public class ReportController {
    private final ReportService reportService;

    @ApiOperation(value = ApiDoc.REPORT_CREATE)
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<ReportResponse> createReport(@RequestBody final AddReportDto dto) {
        ReportResponse response = reportService.createReport(dto);

        return ResponseEntity.ok(response);
    }

    @ApiOperation(value = ApiDoc.REPORT_HISTORY)
    @PostMapping(value = "history")
    public List<ReportGetResDto> getReportDto(@RequestBody MemberUidDTO memberUidDTO) {
        return reportService.getReport(memberUidDTO.getUid());
    }

    @ApiOperation(value = ApiDoc.REPORT_UPDATE)
    @PutMapping(value = "history/{reportId}")
    public ResponseEntity<String> updateReport(@PathVariable Long reportId, @RequestBody UpdateReportDto dto) {
        String result = reportService.updateReport(reportId, dto);

        return ResponseEntity.ok(result);
    }

    @ApiOperation(value = ApiDoc.REPORT_DELETE)
    @PutMapping(value = "{reportId}")
    public ResponseEntity<String> deleteReport(@PathVariable Long reportId, @RequestBody MemberUidDTO memberUidDTO) {
        String result = reportService.deleteReport(reportId, memberUidDTO.getUid());

        return ResponseEntity.ok(result);
    }
}

package com.itaxi.server.report.application;

import com.itaxi.server.exception.member.MemberNotFoundException;
import com.itaxi.server.exception.report.MemberNotWriterException;
import com.itaxi.server.exception.report.ReportNotFoundException;
import com.itaxi.server.member.domain.Member;
import com.itaxi.server.member.domain.repository.MemberRepository;
import com.itaxi.server.report.application.dto.AddReportDto;
import com.itaxi.server.report.application.dto.AddReportMemberDto;
import com.itaxi.server.report.application.dto.ReportGetResDto;
import com.itaxi.server.report.application.dto.UpdateReportDto;
import com.itaxi.server.report.domain.Report;
import com.itaxi.server.report.domain.repository.ReportRepository;

import com.itaxi.server.report.presentation.response.ReportResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReportService {
    private final ReportRepository reportRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public ReportResponse createReport(AddReportDto dto) {
        Optional<Member> writer = memberRepository.findMemberByUid(dto.getWriterUid());
        Optional<Member> reportedMember = memberRepository.findMemberByUid(dto.getReportedUid());
        Report report = null;
        if (writer.isPresent() && reportedMember.isPresent()) {
            AddReportMemberDto reportMemberDto = new AddReportMemberDto(dto, writer.get(), reportedMember.get());
            Member member = reportedMember.get();
            member.setPenalty(member.getPenalty() + 1);
            memberRepository.save(member);
            report = reportRepository.save(reportMemberDto.toEntity());
        } else {
            throw new MemberNotFoundException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        ReportResponse response = new ReportResponse(report);

        return response;
    }

    @Transactional
    public List<ReportGetResDto> getReport(String uid) {
        Optional<Member> member = memberRepository.findMemberByUid(uid);
        if(!member.isPresent())
            throw new MemberNotFoundException(HttpStatus.INTERNAL_SERVER_ERROR);
        Long id = member.get().getId();
        List<Report> posts = reportRepository.findAllByWriterId(id);
        List<ReportGetResDto> results = posts.stream().map(m -> new ReportGetResDto(m)).collect(Collectors.toList());

        return results;
    }

    @Transactional
    public String updateReport(Long reportId, UpdateReportDto dto) {
        Report reportInfo = null;
        Member memberInfo = null;
        Optional<Report> report = reportRepository.findById(reportId);
        if (report.isPresent()) {
            reportInfo = report.get();
        } else {
            throw new ReportNotFoundException(HttpStatus.BAD_REQUEST);
        }
        Optional<Member> member = memberRepository.findMemberByUid(dto.getUid());
        if (member.isPresent()) {
            memberInfo = member.get();
        } else {
            throw new MemberNotFoundException(HttpStatus.BAD_REQUEST);
        }
        if (reportInfo.getWriter().getId() == memberInfo.getId()) {
            reportInfo.setContent(dto.getContent());
            reportInfo.setTitle(dto.getTitle());
            reportRepository.save(reportInfo);
        } else {
            throw new MemberNotWriterException(HttpStatus.BAD_REQUEST);
        }

        return "Success";
    }

    @Transactional
    public String deleteReport(Long reportId, String uid) {
        Report reportInfo = null;
        Member memberInfo = null;
        Optional<Report> report = reportRepository.findById(reportId);
        if (report.isPresent()) {
            reportInfo = report.get();
        } else {
            throw new ReportNotFoundException(HttpStatus.BAD_REQUEST);
        }
        Optional<Member> member = memberRepository.findMemberByUid(uid);
        if (member.isPresent()) {
            memberInfo = member.get();
        } else {
            throw new MemberNotFoundException(HttpStatus.BAD_REQUEST);
        }
        if (reportInfo.getWriter().getId() == memberInfo.getId()) {
            reportInfo.setDeleted(true);
            reportRepository.save(reportInfo);
        } else {
            throw new MemberNotWriterException(HttpStatus.BAD_REQUEST);
        }

        return "Success";
    }
}

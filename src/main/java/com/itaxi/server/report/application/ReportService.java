package com.itaxi.server.report.application;

import com.itaxi.server.exception.member.MemberNotFoundException;
import com.itaxi.server.exception.report.MemberNotWriterException;
import com.itaxi.server.exception.report.ReportNotFoundException;
import com.itaxi.server.member.domain.Member;
import com.itaxi.server.member.domain.repository.MemberRepository;
import com.itaxi.server.post.domain.Post;
import com.itaxi.server.report.application.dto.AddReportDto;
import com.itaxi.server.report.application.dto.AddReportMemberDto;
import com.itaxi.server.report.application.dto.ReportGetResDto;
import com.itaxi.server.report.application.dto.UpdateReportDto;
import com.itaxi.server.report.domain.Report;
import com.itaxi.server.report.domain.repository.ReportRepository;

import com.itaxi.server.report.presentation.response.MemberResponse;
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
        // 신고한 사람과 당한 사람 둘다 존재하는지 체크
        Optional<Member> writer = memberRepository.findMemberByUid(dto.getWriterUid());
        Optional<Member> reportedMember = memberRepository.findMemberByUid(dto.getReportedUid());
        Report report = null;
        if (writer.isPresent() && reportedMember.isPresent()) {
            AddReportMemberDto reportMemberDto = new AddReportMemberDto(dto, writer.get(), reportedMember.get());
            // 신고 당한 사람의 페널티를 +1
            Member member = reportedMember.get();
            member.setPenalty(member.getPenalty() + 1);
            // 멤버와 리포트를 저장
            memberRepository.save(member);
            report = reportRepository.save(reportMemberDto.toEntity());
        } else {
            throw new MemberNotFoundException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        ReportResponse response = new ReportResponse(
                report.getId(),
                new MemberResponse(report.getWriter().getId(), report.getWriter().getName()),
                new MemberResponse(report.getReportedMember().getId(), report.getReportedMember().getName()),
                        report.getDate(), report.getTitle(), report.getContent());

        return response;
    }

    @Transactional
    public List<ReportGetResDto> getReport(String uid) {
        // 해당 uid를 가진 멤버 존재 확인
        Optional<Member> member = memberRepository.findMemberByUid(uid);
        if(!member.isPresent())
            throw new MemberNotFoundException(HttpStatus.INTERNAL_SERVER_ERROR);
        // 멤버 아이디 가져오기
        Long id = member.get().getId();
        // 자신의 신고내역을 보내준다
        List<Report> posts = reportRepository.findAllByWriterId(id);
        List<ReportGetResDto> results = posts.stream().map(m -> new ReportGetResDto(m)).collect(Collectors.toList());

        return results;
    }

    @Transactional
    public String updateReport(Long reportId, UpdateReportDto dto) {
        Report reportInfo = null;
        Member memberInfo = null;
        // report 존재 체크
        Optional<Report> report = reportRepository.findById(reportId);
        if (report.isPresent()) {
            reportInfo = report.get();
        } else {
            throw new ReportNotFoundException(HttpStatus.BAD_REQUEST);
        }
        // member 가져와서 존재 체크
        Optional<Member> member = memberRepository.findMemberByUid(dto.getUid());
        if (member.isPresent()) {
            memberInfo = member.get();
        } else {
            throw new MemberNotFoundException(HttpStatus.BAD_REQUEST);
        }
        // 멤버가 작성한 사람인지 체크
        if (reportInfo.getWriter().getId() == memberInfo.getId()) {
            // 작성한 사람이면 content랑 title 변경하고 저장
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
        // report 존재 체크
        Optional<Report> report = reportRepository.findById(reportId);
        if (report.isPresent()) {
            reportInfo = report.get();
        } else {
            throw new ReportNotFoundException(HttpStatus.BAD_REQUEST);
        }
        // member 가져와서 존재 체크
        Optional<Member> member = memberRepository.findMemberByUid(uid);
        if (member.isPresent()) {
            memberInfo = member.get();
        } else {
            throw new MemberNotFoundException(HttpStatus.BAD_REQUEST);
        }
        // 멤버가 작성한 사람인지 체크
        if (reportInfo.getWriter().getId() == memberInfo.getId()) {
            // 작성한 사람이면 content랑 title 변경하고 저장
            reportInfo.setDeleted(true);
            reportRepository.save(reportInfo);
        } else {
            throw new MemberNotWriterException(HttpStatus.BAD_REQUEST);
        }

        return "Success";
    }
}

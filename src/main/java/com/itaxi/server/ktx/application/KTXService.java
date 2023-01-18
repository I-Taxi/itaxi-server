package com.itaxi.server.ktx.application;

import com.itaxi.server.exception.ktx.*;
import com.itaxi.server.exception.place.PlaceNotFoundException;
import com.itaxi.server.exception.place.PlaceParamException;
import com.itaxi.server.exception.post.JoinerDuplicateMemberException;
import com.itaxi.server.exception.post.JoinerNotFoundException;
import com.itaxi.server.ktx.application.dto.*;
import com.itaxi.server.ktx.domain.KTXJoiner;
import com.itaxi.server.ktxPlace.application.KTXPlaceService;
import com.itaxi.server.ktxPlace.domain.KTXPlace;
import com.itaxi.server.ktxPlace.domain.repository.KTXPlaceRepository;
import com.itaxi.server.exception.member.MemberNotFoundException;
import com.itaxi.server.ktx.domain.KTX;
import com.itaxi.server.ktx.domain.repository.KTXJoinerRepository;
import com.itaxi.server.ktx.domain.repository.KTXRepository;
import com.itaxi.server.member.application.dto.MemberKTXJoinInfo;
import com.itaxi.server.member.domain.Member;
import com.itaxi.server.member.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class KTXService {
    private final KTXRepository ktxRepository;
    private final KTXPlaceRepository ktxPlaceRepository;
    private final MemberRepository memberRepository;
    private final KTXJoinerRepository ktxJoinerRepository;
    private final KTXPlaceService ktxPlaceService;

    @Transactional
    public List<KTXLog> getKTXLog(String uid) {
        Optional<Member> member = memberRepository.findMemberByUid(uid);
        if (!member.isPresent()) {
            throw new MemberNotFoundException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        MemberKTXJoinInfo joinInfo = new MemberKTXJoinInfo(member.get());
        List<KTXLog> ktxLogs = new ArrayList<>();
        PriorityQueue<KTXLog> kQueue = new PriorityQueue<>(Collections.reverseOrder());
        for (KTX ktx : joinInfo.getKtxes()) {
            kQueue.add(new KTXLog(ktx));
        }
        while (kQueue.size() > 0) {
            ktxLogs.add(kQueue.poll());
        }
        return ktxLogs;
    }

    @Transactional
    public KTXLogDetail getKTXLogDetail(Long ktxId) {
        Optional<KTX> ktx = ktxRepository.findById(ktxId);
        if (!ktx.isPresent()) {
            throw new KTXNotFoundException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new KTXLogDetail(ktx.get());
    }

    @Transactional
    public KTXInfoResponse createKTX(AddKTXDto dto) {
        if (dto == null) {
            throw new KTXRequestBodyNullException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (dto.getDstId() == dto.getDepId()) {
            throw new SamePlaceException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (dto.getCapacity() > 10 || dto.getCapacity() < 1) {
            throw new WrongCapacityException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        Period period = getPeriod(LocalDateTime.now(), dto.getDeptTime());
        if (period.getYears() >= 1 || period.getMonths() >= 3) {
            throw new BadDateException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (dto.getDepId() == null || dto.getDstId() == null || dto.getDeptTime() == null || dto.getUid() == null) {
            throw new PlaceParamException();
        }

        final KTXPlace departure = ktxPlaceRepository.findById(dto.getDepId()).orElseThrow(PlaceNotFoundException::new);
        final KTXPlace destination = ktxPlaceRepository.findById(dto.getDstId()).orElseThrow(PlaceNotFoundException::new);
        AddKTXPlaceDto ktxPlaceDto = new AddKTXPlaceDto(dto, departure, destination);
        KTXResDto result = new KTXResDto(ktxRepository.save(ktxPlaceDto.toEntity()));
        KTXJoinDto joinDto = new KTXJoinDto(dto.getUid(), true);
        KTXInfoResponse response = joinKTX(result.getId(), joinDto);
        ktxPlaceService.updateView(dto.getDepId());
        ktxPlaceService.updateView(dto.getDstId());

        return response;
    }

    @Transactional
    public List<KTXGetResDto> getKTX(final Long depId, final Long dstId, final LocalDate time) {
        final KTXPlace departure = (depId == null) ? null : ktxPlaceRepository.findById(depId).orElseThrow(PlaceNotFoundException::new);
        final KTXPlace destination = (dstId == null) ? null : ktxPlaceRepository.findById(dstId).orElseThrow(PlaceNotFoundException::new);
        final LocalDateTime startDateTime = (Objects.equals(time, LocalDate.now()))? LocalDateTime.of(time, LocalTime.now()):LocalDateTime.of(time, LocalTime.of(0, 0, 0));
        final LocalDateTime endDateTime = LocalDateTime.of(time, LocalTime.of(23, 59, 59));

        List<KTX> ktxes = null;
        if (depId == null && dstId == null) {
            ktxes = ktxRepository.findAllByDeptTimeBetweenOrderByDeptTime(startDateTime, endDateTime);
        } else if (depId == null) {
            ktxes = ktxRepository.findAllByDestinationAndDeptTimeBetweenOrderByDeptTime(destination, startDateTime, endDateTime);
        } else if (dstId == null) {
            ktxes = ktxRepository.findAllByDepartureAndDeptTimeBetweenOrderByDeptTime(departure, startDateTime, endDateTime);
        } else {
            ktxes = ktxRepository.findAllByDepartureAndDestinationAndDeptTimeBetweenOrderByDeptTime(departure, destination, startDateTime, endDateTime);
        }

        List<KTXGetResDto> resultList = ktxes.stream()
                .map(m -> new KTXGetResDto(m))
                .collect(Collectors.toList());

        return resultList;
    }

    @Transactional
    public KTXInfoResponse joinKTX(Long ktxId, KTXJoinDto ktxJoinDto) {
        KTX ktxInfo = null;
        Member memberInfo = null;

        Optional<KTX> ktx = ktxRepository.findById(ktxId);
        if (ktx.isPresent()) {
            ktxInfo = ktx.get();
            if (compareMinute(LocalDateTime.now(), ktxInfo.getDeptTime()) == 1) {
                throw new KTXTimeOutException(HttpStatus.BAD_REQUEST);
            }
        } else {
            throw new KTXNotFoundException(HttpStatus.BAD_REQUEST);
        }

        if (ktxInfo.getStatus() == 2) {
            throw new KTXMemberFullException(HttpStatus.BAD_REQUEST);
        }

        Optional<Member> member = memberRepository.findMemberByUid(ktxJoinDto.getUid());
        if (member.isPresent()) {
            memberInfo = member.get();
        } else {
            throw new MemberNotFoundException(HttpStatus.BAD_REQUEST);
        }

        Optional<KTXJoiner> ktxJoiner = ktxJoinerRepository.findKtxJoinerByKtxAndMember(ktxInfo, memberInfo);
        if (!ktxJoiner.isPresent()) {
            KTXJoinerCreateDto ktxJoinerCreateDto = new KTXJoinerCreateDto(memberInfo, ktxInfo, ktxJoinDto.isOwner());
            ktxJoinerRepository.save(new KTXJoiner(ktxJoinerCreateDto));
        } else {
            throw new JoinerDuplicateMemberException(HttpStatus.BAD_REQUEST);
        }

        List<KTXJoiner> ktxJoiners = ktxJoinerRepository.findKtxJoinerByKtx(ktxInfo);
        ktxInfo.setJoiners(ktxJoiners);

        int joinerSize = ktxInfo.getJoiners().size();
        if (joinerSize >= ktxInfo.getCapacity()) {
            ktxInfo.setStatus(2);
            ktxRepository.save(ktxInfo);
        }

        return ktxInfo.toKTXInfoResponse();
    }

    @Transactional
    public String exitKTX(Long ktxId, String uid) {
        KTX ktxInfo = null;
        Member memberInfo = null;

        Optional<KTX> ktx = ktxRepository.findById(ktxId);
        if (ktx.isPresent()) {
            ktxInfo = ktx.get();
            if (compareMinute(LocalDateTime.now(), ktxInfo.getDeptTime()) == 1) {
                throw new KTXTimeOutException(HttpStatus.BAD_REQUEST);
            }
        } else {
            throw new KTXNotFoundException(HttpStatus.BAD_REQUEST);
        }

        Optional<Member> member = memberRepository.findMemberByUid(uid);
        if (member.isPresent()) {
            memberInfo = member.get();
        } else {
            throw new MemberNotFoundException(HttpStatus.BAD_REQUEST);
        }

        Optional<KTXJoiner> ktxJoiner = ktxJoinerRepository.findKtxJoinerByKtxAndMember(ktxInfo, memberInfo);
        int joinerSize = ktxInfo.getJoiners().size();
        if (ktxJoiner.isPresent()) {
            KTXJoiner joinerInfo = ktxJoiner.get();
            joinerInfo.setStatus(0);
            joinerInfo.setDeleted(true);
            ktxJoinerRepository.save(joinerInfo);

            System.out.println(joinerSize);
            if (joinerSize == 1) {
                ktxInfo.setStatus(0);
                ktxInfo.setDeleted(true);
            } else if (joinerSize == ktxInfo.getCapacity()) {
                ktxInfo.setStatus(1);
            }
            ktxRepository.save(ktxInfo);

            if (joinerSize > 1 && joinerInfo.isOwner()) {
                KTXJoiner joinerBeOwner = ktxInfo.getJoiners().get(1);
                joinerBeOwner.setOwner(true);
                ktxJoinerRepository.save(joinerBeOwner);
            }
        } else {
            throw new JoinerNotFoundException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return "Success";
    }

    @Transactional
    public String stopKTX(Long ktxId, String uid) {
        KTX ktxInfo = null;
        Member memberInfo = null;

        Optional<KTX> ktx = ktxRepository.findById(ktxId);
        if (ktx.isPresent()) {
            ktxInfo = ktx.get();
            if (compareMinute(LocalDateTime.now(), ktxInfo.getDeptTime()) == 1) {
                throw new KTXTimeOutException(HttpStatus.BAD_REQUEST);
            }
        } else {
            throw new KTXNotFoundException(HttpStatus.BAD_REQUEST);
        }

        Optional<Member> member = memberRepository.findMemberByUid(uid);
        if (member.isPresent()) {
            memberInfo = member.get();
        } else {
            throw new MemberNotFoundException(HttpStatus.BAD_REQUEST);
        }

        boolean exists = false;
        boolean isOwner = false;
        List<KTXJoiner> ktxJoiners = ktxInfo.getJoiners();
        for (KTXJoiner ktxJoiner : ktxJoiners) {
            if (ktxJoiner.getMember().getId().equals(memberInfo.getId())) {
                exists = true;
                if (ktxJoiner.isOwner()) isOwner = true;
            }
        }

        if (!exists) {
            throw new JoinerNotFoundException(HttpStatus.BAD_REQUEST);
        }
        if (!isOwner) {
            throw new JoinerNotOwnerException(HttpStatus.BAD_REQUEST);
        }

        ktxInfo.setStatus(2);
        ktxRepository.save(ktxInfo);

        return "Success";
    }

    public static int compareMinute(LocalDateTime date1, LocalDateTime date2) {
        LocalDateTime dayDate1 = date1.truncatedTo(ChronoUnit.MINUTES);
        LocalDateTime dayDate2 = date2.truncatedTo(ChronoUnit.MINUTES);
        int compareResult = dayDate1.compareTo(dayDate2);

        return compareResult;
    }

    private static Period getPeriod(LocalDateTime a, LocalDateTime b) {
        return Period.between(a.toLocalDate(), b.toLocalDate());
    }
}

package com.itaxi.server.ktx.application;

import com.itaxi.server.exception.ktx.JoinerNotOwnerException;
import com.itaxi.server.exception.place.PlaceNotFoundException;
import com.itaxi.server.exception.place.PlaceParamException;
import com.itaxi.server.exception.post.JoinerNotFoundException;
import com.itaxi.server.exception.post.PostMemberFullException;
import com.itaxi.server.exception.post.PostNotFoundException;
import com.itaxi.server.exception.post.PostTimeOutException;
import com.itaxi.server.ktx.application.dto.*;
import com.itaxi.server.ktx.domain.KTXJoiner;
import com.itaxi.server.ktxPlace.domain.KTXPlace;
import com.itaxi.server.ktxPlace.domain.repository.KTXPlaceRepository;
import com.itaxi.server.exception.member.MemberNotFoundException;
import com.itaxi.server.ktx.domain.KTX;
import com.itaxi.server.ktx.domain.repository.KTXJoinerRepository;
import com.itaxi.server.ktx.domain.repository.KTXRepository;
import com.itaxi.server.member.application.dto.MemberKTXJoinInfo;
import com.itaxi.server.member.domain.Member;
import com.itaxi.server.member.domain.repository.MemberRepository;
import com.itaxi.server.place.domain.Place;
import com.itaxi.server.post.application.dto.PostGetResDto;
import com.itaxi.server.post.domain.Joiner;
import com.itaxi.server.post.domain.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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

    @Transactional
    public List<KTXLog> getPostLog(String uid) {
        Optional<Member> member = memberRepository.findMemberByUid(uid);
        if (!member.isPresent()) throw new MemberNotFoundException(HttpStatus.INTERNAL_SERVER_ERROR);
        MemberKTXJoinInfo joinInfo = new MemberKTXJoinInfo(member.get());
        List<KTXLog> ktxLogs = new ArrayList<>();
        PriorityQueue<KTXLog> kQueue = new PriorityQueue<>(Collections.reverseOrder());
        // 출발시각으로 정렬
        for (KTX ktx : joinInfo.getKtxes()) kQueue.add(new KTXLog(ktx));
        // 정렬된 결과를 List에 주입
        while (kQueue.size() > 0) ktxLogs.add(kQueue.poll());
        return ktxLogs;
    }

    @Transactional
    public KTXLogDetail getKTXLogDetail(Long ktxId) {
        Optional<KTX> ktx = ktxRepository.findById(ktxId);
        if (!ktx.isPresent()) throw new PostNotFoundException(HttpStatus.INTERNAL_SERVER_ERROR);
        return new KTXLogDetail(ktx.get());
    }

    @Transactional
    public KTX create(AddKTXPlaceDto dto) {
        return ktxRepository.save(dto.toEntity());
    }

    @Transactional
    public KTXInfoResponse createKTX(AddKTXDto dto) {
        if (dto.getDepId() == null || dto.getDstId() == null || dto.getDeptTime() == null || dto.getUid() == null) {
            throw new PlaceParamException();
        }
        final KTXPlace departure = ktxPlaceRepository.findById(dto.getDepId()).orElseThrow(PlaceNotFoundException::new);
        final KTXPlace destination = ktxPlaceRepository.findById(dto.getDstId()).orElseThrow(PlaceNotFoundException::new);
        AddKTXPlaceDto ktxPlaceDto = new AddKTXPlaceDto(dto, departure, destination);
        KTXResDto result = new KTXResDto(create(ktxPlaceDto));
        KTXJoinDto joinDto = new KTXJoinDto(dto.getUid(), true);
        KTXInfoResponse response = joinKTX(result.getId(), joinDto);

        return response;
    }

    @Transactional
    public List<KTXGetResDto> getKTX(final Long depId, final Long dstId, final LocalDate time) {
        final KTXPlace departure = (depId == null) ? null : ktxPlaceRepository.findById(depId).orElseThrow(PlaceNotFoundException::new);
        final KTXPlace destination = (dstId == null) ? null : ktxPlaceRepository.findById(dstId).orElseThrow(PlaceNotFoundException::new);
        final LocalDateTime startDateTime = (Objects.equals(time, LocalDate.now()))? LocalDateTime.of(time, LocalTime.now()):LocalDateTime.of(time, LocalTime.of(0, 0, 0));
        final LocalDateTime endDateTime = LocalDateTime.of(time, LocalTime.of(23, 59, 59));

        List<KTX> ktxes =
                ((depId == null && dstId == null)? (ktxRepository.findAllByDeptTimeBetweenOrderByDeptTime(startDateTime, endDateTime)):
                        (depId == null ? (ktxRepository.findAllByDestinationAndDeptTimeBetweenOrderByDeptTime(destination, startDateTime, endDateTime)):
                                (dstId == null ? (ktxRepository.findAllByDepartureAndDeptTimeBetweenOrderByDeptTime(departure, startDateTime, endDateTime)) :
                                        (ktxRepository.findAllByDepartureAndDestinationAndDeptTimeBetweenOrderByDeptTime(departure, destination, startDateTime, endDateTime))
                                )));

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
                throw new PostTimeOutException(HttpStatus.BAD_REQUEST);
            }
        } else {
            throw new PostNotFoundException(HttpStatus.BAD_REQUEST);
        }

        if (ktxInfo.getStatus() == 2) {
            throw new PostMemberFullException(HttpStatus.BAD_REQUEST);
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
            return ktxInfo.toKTXInfoResponse();
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
                throw new PostTimeOutException(HttpStatus.BAD_REQUEST);
            }
        } else {
            throw new PostNotFoundException(HttpStatus.BAD_REQUEST);
        }

        Optional<Member> member = memberRepository.findMemberByUid(uid);
        if (member.isPresent()) {
            memberInfo = member.get();
        } else {
            throw new MemberNotFoundException(HttpStatus.BAD_REQUEST);
        }

        Optional<KTXJoiner> ktxJoiner = ktxJoinerRepository.findKtxJoinerByKtxAndMember(ktxInfo, memberInfo);
        int joinerSize = ktxInfo.getJoiners().size();
        if (ktxJoiner.isPresent()) { // 멤버가 이 포스트에 존재하면
            KTXJoiner joinerInfo = ktxJoiner.get();
            joinerInfo.setStatus(0); // 포스트에서 나가고
            joinerInfo.setDeleted(true); // joiner에서 지우기
            ktxJoinerRepository.save(joinerInfo);

            System.out.println(joinerSize);
            if (joinerSize == 1) { // 아무도 안 남았으면
                ktxInfo.setStatus(0); // 모집 종료하고
                ktxInfo.setDeleted(true); // 지우기
            } else if (joinerSize == ktxInfo.getCapacity()) {
                ktxInfo.setStatus(1); // 아니면 아직 모집 중
            }
            ktxRepository.save(ktxInfo);

            if (joinerSize > 1 && joinerInfo.isOwner()) { // 아직 사람들 남았는데 주인이 나갔으면
                KTXJoiner joinerBeOwner = ktxInfo.getJoiners().get(1); // 가장 처음으로? 들어온 멤버를
                joinerBeOwner.setOwner(true); // 주인으로 만들고 저장
                ktxJoinerRepository.save(joinerBeOwner);
            }
        } else {
            throw new JoinerNotFoundException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return "Success";
    }

    @Transactional
    public String stopKTX(Long ktxId, String uid) {
        // ktx 포스트가 존재하는지 확인
        KTX ktxInfo = null;
        Member memberInfo = null;

        Optional<KTX> ktx = ktxRepository.findById(ktxId);
        if (ktx.isPresent()) {
            ktxInfo = ktx.get();
            if (compareMinute(LocalDateTime.now(), ktxInfo.getDeptTime()) == 1) {
                throw new PostTimeOutException(HttpStatus.BAD_REQUEST);
            }
        } else {
            throw new PostNotFoundException(HttpStatus.BAD_REQUEST);
        }
        // 멤버가 존재하는지 확인
        Optional<Member> member = memberRepository.findMemberByUid(uid);
        if (member.isPresent()) {
            memberInfo = member.get();
        } else {
            throw new MemberNotFoundException(HttpStatus.BAD_REQUEST);
        }
        // 멤버가 ktxJoiner에 존재하는지 확인
        boolean exists = false;
        boolean isOwner = false;
        List<KTXJoiner> ktxJoiners = ktxInfo.getJoiners();
        for (KTXJoiner ktxJoiner : ktxJoiners) {
            if (ktxJoiner.getMember().getId().equals(memberInfo.getId())) {
                exists = true;
                // 멤버가 ktxJoiner에 존재한다면 owner인지 확인
                if (ktxJoiner.isOwner()) isOwner = true;
            }
        }

        if (!exists) {
            throw new JoinerNotFoundException(HttpStatus.BAD_REQUEST);
        }
        if (!isOwner) {
            throw new JoinerNotOwnerException(HttpStatus.BAD_REQUEST);
        }
        // 멤버가 owner이면 모집 중단
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
}

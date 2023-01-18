package com.itaxi.server.post.application;

import com.itaxi.server.exception.ktx.BadDateException;
import com.itaxi.server.exception.ktx.JoinerNotOwnerException;
import com.itaxi.server.exception.place.PlaceParamException;
import com.itaxi.server.exception.post.*;
import com.itaxi.server.exception.place.PlaceNotFoundException;
import com.itaxi.server.ktx.domain.KTX;
import com.itaxi.server.member.application.dto.MemberKTXJoinInfo;
import com.itaxi.server.place.application.PlaceService;
import com.itaxi.server.place.domain.Place;
import com.itaxi.server.place.domain.repository.PlaceRepository;
import com.itaxi.server.post.application.dto.*;
import com.itaxi.server.post.application.dto.StopoverCreateDto;
import com.itaxi.server.post.domain.Post;
import com.itaxi.server.member.domain.Member;
import com.itaxi.server.member.domain.repository.MemberRepository;
import com.itaxi.server.post.domain.Joiner;
import com.itaxi.server.post.domain.Stopover;
import com.itaxi.server.post.domain.repository.JoinerRepository;
import com.itaxi.server.post.domain.repository.PostRepository;
import com.itaxi.server.post.domain.repository.StopoverRepository;
import com.itaxi.server.post.presentation.response.PostInfoResponse;
import com.itaxi.server.exception.member.MemberNotFoundException;
import com.itaxi.server.member.application.dto.MemberJoinInfo;
import com.itaxi.server.post.application.dto.PostLog;
import com.itaxi.server.post.application.dto.PostLogDetail;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final PlaceRepository placeRepository;
    private final MemberRepository memberRepository;
    private final JoinerRepository joinerRepository;
    private final StopoverRepository stopoverRepository;
    private final PlaceService placeService;

    @Transactional
    public List<PostLog> getPostLog(String uid) {
        Optional<Member> member = memberRepository.findMemberByUid(uid);
        if(!member.isPresent())
            throw new MemberNotFoundException(HttpStatus.INTERNAL_SERVER_ERROR);
        MemberJoinInfo joinInfo = new MemberJoinInfo(member.get());
        MemberKTXJoinInfo ktxJoinInfo = new MemberKTXJoinInfo(member.get());
        List<PostLog> postLogs = new ArrayList<>();
        PriorityQueue<PostLog> pQueue = new PriorityQueue<>(Collections.reverseOrder());
        for(Post post : joinInfo.getPosts())
            pQueue.add(new PostLog(post));
        for (KTX ktx : ktxJoinInfo.getKtxes())
            pQueue.add(new PostLog(ktx));
        while(pQueue.size() > 0)
            postLogs.add(pQueue.poll());
        return postLogs;
    }

    @Transactional
    public PostLogDetail getPostLogDetail(Long postId) {
        Optional<Post> post = postRepository.findById(postId);
        if(!post.isPresent())
            throw new PostNotFoundException(HttpStatus.INTERNAL_SERVER_ERROR);
        return new PostLogDetail(post.get());
    }

    @Transactional
    public PostInfoResponse createPost(AddPostDto dto) {
        if (dto.getStopoverIds().size() > 3) throw new TooManyStopoversException(HttpStatus.INTERNAL_SERVER_ERROR);
        Period period = getPeriod(LocalDateTime.now(), dto.getDeptTime());
        if (period.getYears() >= 1 || period.getMonths() >= 3) throw new BadDateException(HttpStatus.INTERNAL_SERVER_ERROR);
        if (dto.getDepId() == null || dto.getDstId() == null || dto.getPostType() == null || dto.getDeptTime() == null || dto.getUid() == null)
            throw new PlaceParamException();

        final Place departure = placeRepository.findById(dto.getDepId()).orElseThrow(PlaceNotFoundException::new);
        final Place destination = placeRepository.findById(dto.getDstId()).orElseThrow(PlaceNotFoundException::new);
        AddPostPlaceDto postPlaceDto = new AddPostPlaceDto(dto, departure, destination);
        ResDto result = new ResDto(postRepository.save(postPlaceDto.toEntity()));
        List<Long> stopoverList = dto.getStopoverIds();
        Optional<Post> post = postRepository.findById(result.getId());
        Post resPost = post.get();
        Place place = null;
        Stopover stopover = null;
        for (Long id : stopoverList) {
            place = placeRepository.findById(id).orElseThrow(PlaceNotFoundException::new);
            stopover = new Stopover(new StopoverCreateDto(place, resPost));
            stopoverRepository.save(stopover);
        }
        List<Stopover> stopovers = stopoverRepository.findStopoversByPost(resPost);
        resPost.setStopovers(stopovers);

        PostJoinDto joinDto= new PostJoinDto(dto.getUid(), true);
        PostInfoResponse response = joinPost(result.getId(), joinDto);

        List<Long> stopoverIds = dto.getStopoverIds();
        for (Long id : stopoverIds) {
            placeService.updateView(id);
        }
        placeService.updateView(dto.getDepId());
        placeService.updateView(dto.getDstId());

        return response;
    }

    @Transactional
    public List<PostGetResDto> getPost(final Long depId, final Long dstId,  final LocalDate time, final Integer postType) {
        final Place departure = (depId == null) ? null : placeRepository.findById(depId).orElseThrow(PlaceNotFoundException::new);
        final Place destination = (dstId == null) ? null : placeRepository.findById(dstId).orElseThrow(PlaceNotFoundException::new);
        final LocalDateTime startDateTime = (Objects.equals(time, LocalDate.now()))? LocalDateTime.of(time, LocalTime.now()):LocalDateTime.of(time, LocalTime.of(0, 0, 0));
        final LocalDateTime endDateTime = LocalDateTime.of(time, LocalTime.of(23, 59, 59));

        List<Post> posts = (postType == null) ?
                ((depId == null && dstId == null)? (postRepository.findAllByDeptTimeBetweenOrderByDeptTime(startDateTime, endDateTime)):
                        (depId == null ? (postRepository.findAllByDestinationAndDeptTimeBetweenOrderByDeptTime(destination, startDateTime, endDateTime)):
                                (dstId == null ? (postRepository.findAllByDepartureAndDeptTimeBetweenOrderByDeptTime(departure, startDateTime, endDateTime)) :
                                        (postRepository.findAllByDepartureAndDestinationAndDeptTimeBetweenOrderByDeptTime(departure, destination, startDateTime, endDateTime))
                                ))) :
                (depId == null && dstId == null? (postRepository.findAllByPostTypeAndDeptTimeBetweenOrderByDeptTime(postType, startDateTime, endDateTime)):
                        (depId == null ? (postRepository.findAllByPostTypeAndDestinationAndDeptTimeBetweenOrderByDeptTime(postType, destination, startDateTime, endDateTime)):
                                (dstId == null ? (postRepository.findAllByPostTypeAndDepartureAndDeptTimeBetweenOrderByDeptTime(postType, departure, startDateTime, endDateTime)) :
                                        (postRepository.findAllByPostTypeAndDepartureAndDestinationAndDeptTimeBetweenOrderByDeptTime(postType, departure, destination, startDateTime, endDateTime))
                                )));

        List<PostGetResDto> resultList = posts.stream()
                .map(m -> new PostGetResDto(m))
                .collect(Collectors.toList());

        return resultList;
    }

    @Transactional
    public PostInfoResponse joinPost(Long postId, PostJoinDto postJoinDto) {
        Post postInfo = null;
        Member memberInfo = null;

        Optional<Post> post = postRepository.findById(postId);
        if (post.isPresent()) {
            postInfo = post.get();
            if (compareMinute(LocalDateTime.now(), postInfo.getDeptTime()) == 1) {
                throw new PostTimeOutException(HttpStatus.BAD_REQUEST);
            }
        } else {
            throw new PostNotFoundException(HttpStatus.BAD_REQUEST);
        }

        if (postInfo.getStatus() == 2) {
            throw new PostMemberFullException(HttpStatus.BAD_REQUEST);
        }

        Optional<Member> member = memberRepository.findMemberByUid(postJoinDto.getUid());
        if (member.isPresent()) {
            memberInfo = member.get();
        } else {
            throw new MemberNotFoundException(HttpStatus.BAD_REQUEST);
        }

        Optional<Joiner> joiner = joinerRepository.findJoinerByPostAndMember(postInfo, memberInfo);
        if (!joiner.isPresent()) {
            JoinerCreateDto joinerCreateDto = new JoinerCreateDto(memberInfo, postInfo, postJoinDto.isOwner());
            joinerRepository.save(new Joiner(joinerCreateDto));
        } else {
            return postInfo.toPostInfoResponse();
        }

        List<Joiner> joiners = joinerRepository.findJoinersByPost(postInfo);
        postInfo.setJoiners(joiners);

        int joinerSize = postInfo.getJoiners().size();
        if (joinerSize >= postInfo.getCapacity()) {
            postInfo.setStatus(2);
            postRepository.save(postInfo);
        }

        return postInfo.toPostInfoResponse();
    }

    @Transactional
    public Member exitPost(Long postId, String uid) {
        Post postInfo = null;
        Member memberInfo = null;
        Member newOwner = null;
        Joiner joinerBeOwner = null;

        Optional<Post> post = postRepository.findById(postId);
        if (post.isPresent()) {
            postInfo = post.get();
            if (compareMinute(LocalDateTime.now(), postInfo.getDeptTime()) == 1) {
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

        Optional<Joiner> joiner = joinerRepository.findJoinerByPostAndMember(postInfo, memberInfo);
        int joinerSize = postInfo.getJoiners().size();
        if (joiner.isPresent()) {
            Joiner joinerInfo = joiner.get();
            joinerInfo.setStatus(0);
            joinerInfo.setDeleted(true);
            joinerRepository.save(joinerInfo);

            System.out.println(joinerSize);
            if (joinerSize == 1){
                postInfo.setStatus(0);
                postInfo.setDeleted(true);
            }  else if (joinerSize == postInfo.getCapacity()) {
                postInfo.setStatus(1);
            }
            postRepository.save(postInfo);

            if (joinerSize > 1 && joinerInfo.isOwner()) {
                joinerBeOwner = postInfo.getJoiners().get(1);
                joinerBeOwner.setOwner(true);
                joinerRepository.save(joinerBeOwner);
                newOwner = joinerBeOwner.getMember();
            }
        } else {
            throw new JoinerNotFoundException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return newOwner;
    }

    @Transactional
    public String changePostTime(Long postId, PostTimeDto dto) {
        Post postInfo = null;
        Member memberInfo = null;

        Optional<Post> post = postRepository.findById(postId);
        if (post.isPresent()) {
            postInfo = post.get();
            if (compareMinute(LocalDateTime.now(), postInfo.getDeptTime()) == 1) {
                throw new PostTimeOutException(HttpStatus.BAD_REQUEST);
            }
        } else {
            throw new PostNotFoundException(HttpStatus.BAD_REQUEST);
        }

        Optional<Member> member = memberRepository.findMemberByUid(dto.getUid());
        if (member.isPresent()) {
            memberInfo = member.get();
        } else {
            throw new MemberNotFoundException(HttpStatus.BAD_REQUEST);
        }

        long checkChangeMinutes = ChronoUnit.MINUTES.between(LocalDateTime.now(), postInfo.getDeptTime());
        if (checkChangeMinutes < 3) {
            throw new CannotChangeDeptTimeException(HttpStatus.BAD_REQUEST);
        }

        long minutes = ChronoUnit.MINUTES.between(postInfo.getDeptTime(), dto.getDeptTime());
        if (minutes >= 30) {
            throw new DeptTimeWrongException(HttpStatus.BAD_REQUEST);
        }

        Optional<Joiner> joiner = joinerRepository.findJoinerByPostAndMember(postInfo, memberInfo);
        if (joiner.isPresent()) {
            Joiner joinerInfo = joiner.get();
            if (joinerInfo.isOwner()) {
                postInfo.setDeptTime(dto.getDeptTime());
                postRepository.save(postInfo);
            } else {
                throw new JoinerNotOwnerException(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            throw new JoinerNotFoundException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

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
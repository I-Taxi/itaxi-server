package com.itaxi.server.post.application;

import com.itaxi.server.exception.place.PlaceParamException;
import com.itaxi.server.exception.post.*;
import com.itaxi.server.exception.place.PlaceNotFoundException;
import com.itaxi.server.place.domain.Place;
import com.itaxi.server.place.domain.repository.PlaceRepository;
import com.itaxi.server.post.application.dto.*;
import com.itaxi.server.post.domain.Post;
import com.itaxi.server.member.domain.Member;
import com.itaxi.server.member.domain.repository.MemberRepository;
import com.itaxi.server.post.domain.Joiner;
import com.itaxi.server.post.domain.repository.JoinerRepository;
import com.itaxi.server.post.domain.repository.PostRepository;
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

    @Transactional
    public List<PostLog> getPostLog(String uid) {
        Optional<Member> member = memberRepository.findMemberByUid(uid);
        if(!member.isPresent())
            throw new MemberNotFoundException(HttpStatus.INTERNAL_SERVER_ERROR);
        MemberJoinInfo joinInfo = new MemberJoinInfo(member.get());
        List<PostLog> postLogs = new ArrayList<>();
        PriorityQueue<PostLog> pQueue = new PriorityQueue<>(Collections.reverseOrder());
        // 출발시각(deptTime) 기준으로 정렬
        for(Post post : joinInfo.getPosts())
            pQueue.add(new PostLog(post));
        // 정렬된 결과를 List에 주입
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
    public Post create(AddPostPlaceDto dto) {
        return postRepository.save(dto.toEntity());
    }

    @Transactional
    public PostInfoResponse createPost(AddPostDto dto) {
        if (dto.getDepId() == null || dto.getDstId() == null || dto.getPostType() == null || dto.getDeptTime() == null || dto.getUid() == null)
            throw new PlaceParamException();

        final Place departure = placeRepository.findById(dto.getDepId()).orElseThrow(PlaceNotFoundException::new);
        final Place destination = placeRepository.findById(dto.getDstId()).orElseThrow(PlaceNotFoundException::new);
        AddPostPlaceDto postPlaceDto = new AddPostPlaceDto(dto, departure, destination);
        ResDto result = new ResDto(create(postPlaceDto));
        PostJoinDto joinDto= new PostJoinDto(dto.getUid(), dto.getLuggage(), true);
        PostInfoResponse response = joinPost(result.getId(), joinDto);

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
                .map(m -> new PostGetResDto(m, m.getJoiners().stream().map(Joiner::getLuggage).collect(Collectors.toList())))
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
            if (compareMinute(LocalDateTime.now(), postInfo.getDeptTime()) == 1) {      // 1 : 시간 지남
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
            JoinerCreateDto joinerCreateDto = new JoinerCreateDto(memberInfo, postInfo, postJoinDto.getLuggage(), postJoinDto.isOwner());
            joinerRepository.save(new Joiner(joinerCreateDto));
        } else {
            return postInfo.toPostInfoResponse();
        }

        List<Joiner> joiners = joinerRepository.findJoinersByPost(postInfo);
        postInfo.setJoiners(joiners);

        int joinerSize = postInfo.getJoiners().size();
        if (joinerSize >= postInfo.getCapacity()) {
            postInfo.setStatus(2);                          // 2 : 모집 완료
            postRepository.save(postInfo);
        }

        return postInfo.toPostInfoResponse();
    }

    @Transactional
    public Joiner exitPost(Long postId, String uid) {
        Post postInfo = null;
        Member memberInfo = null;
        Joiner joinerBeOwner = null;

        Optional<Post> post = postRepository.findById(postId);
        if (post.isPresent()) {
            postInfo = post.get();
            if (compareMinute(LocalDateTime.now(), postInfo.getDeptTime()) == 1) {      // 1 : 시간 지남
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
                postInfo.setStatus(0);                              // 0 : 모집 종료
                postInfo.setDeleted(true);
            }  else if (joinerSize == postInfo.getCapacity()) {
                postInfo.setStatus(1);                              // 1 : 모집 중
            }
            postRepository.save(postInfo);

            if (joinerSize > 1 && joinerInfo.isOwner()) {
                joinerBeOwner = postInfo.getJoiners().get(1);
                joinerBeOwner.setOwner(true);
                joinerRepository.save(joinerBeOwner);
            }
        } else {
            throw new JoinerNotFoundException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return joinerBeOwner;
    }

    public static int compareMinute(LocalDateTime date1, LocalDateTime date2) {
        LocalDateTime dayDate1 = date1.truncatedTo(ChronoUnit.MINUTES);
        LocalDateTime dayDate2 = date2.truncatedTo(ChronoUnit.MINUTES);
        int compareResult = dayDate1.compareTo(dayDate2);

        return compareResult;
    }
}
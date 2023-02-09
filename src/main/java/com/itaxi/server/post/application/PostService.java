package com.itaxi.server.post.application;

import com.itaxi.server.exception.joiner.JoinerDuplicateMemberException;
import com.itaxi.server.exception.joiner.JoinerNotFoundException;
import com.itaxi.server.exception.joiner.JoinerNotOwnerException;
import com.itaxi.server.exception.ktx.*;
import com.itaxi.server.exception.place.PlaceParamException;
import com.itaxi.server.exception.post.*;
import com.itaxi.server.exception.place.PlaceNotFoundException;
import com.itaxi.server.exception.stopover.StopoverTooManyException;
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
import com.itaxi.server.post.presentation.request.PostGetLogDetailRequest;
import com.itaxi.server.post.presentation.response.PostInfoResponse;
import com.itaxi.server.exception.member.MemberNotFoundException;
import com.itaxi.server.member.application.dto.MemberJoinInfo;
import com.itaxi.server.post.application.dto.PostLog;
import com.itaxi.server.post.application.dto.PostLogDetail;
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
        if(!member.isPresent()) {
            throw new MemberNotFoundException();
        }
        if (member.get().isDeleted())
            throw new MemberNotFoundException();
        MemberJoinInfo joinInfo = new MemberJoinInfo(member.get());
        MemberKTXJoinInfo ktxJoinInfo = new MemberKTXJoinInfo(member.get());
        List<PostLog> postLogs = new ArrayList<>();
        PriorityQueue<PostLog> pQueue = new PriorityQueue<>(Collections.reverseOrder());
        for(Post post : joinInfo.getPosts()) {
            pQueue.add(new PostLog(post));
        }
        for (KTX ktx : ktxJoinInfo.getKtxes()) {
            pQueue.add(new PostLog(ktx));
        }
        while(pQueue.size() > 0) {
            postLogs.add(pQueue.poll());
        }
        return postLogs;
    }

    @Transactional
    public PostLogDetail getPostLogDetail(Long postId, PostGetLogDetailRequest request) {
        Optional<Post> post = postRepository.findById(postId);

        boolean check = false;
        for(int i =  0; i<post.get().getJoiners().size(); i++){
            if(post.get().getJoiners().get(i).getMember().getUid().equals(request.getUid())){
                check = true;
            }
        }

        if(!post.isPresent()) {
            throw new PostNotFoundException();
        }
        if(check == false ){
            throw new PostNoAuthorityException();
        }

        return new PostLogDetail(post.get());
    }

    @Transactional
    public PostInfoResponse createPost(AddPostDto dto) {
        if (dto.getStopoverIds().size() > 1) {
            throw new StopoverTooManyException();
        }
        if (dto.getDstId() == dto.getDepId()) {
            throw new KTXDuplicatePlaceException();
        }
        Period period = getPeriod(LocalDateTime.now(), dto.getDeptTime());
        if (period.getYears() >= 1 || period.getMonths() >= 3) {
            throw new KTXBadDateException();
        }
        if (dto.getDepId() == null || dto.getDstId() == null || dto.getPostType() == null || dto.getDeptTime() == null || dto.getUid() == null) {
            throw new PlaceParamException();
        }

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

        PostJoinDto joinDto = new PostJoinDto(dto.getUid(), true);
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

        List<Post> posts = null;

        if (postType == null) {
            if (depId == null && dstId == null) {
                posts = postRepository.findAllByDeptTimeBetweenOrderByDeptTime(startDateTime, endDateTime);
            } else if (depId == null) {
                posts = postRepository.findAllByDestinationAndDeptTimeBetweenOrderByDeptTime(destination, startDateTime, endDateTime);
            } else if (dstId == null) {
                posts = postRepository.findAllByDepartureAndDeptTimeBetweenOrderByDeptTime(departure, startDateTime, endDateTime);
            } else {
                posts = postRepository.findAllByDepartureAndDestinationAndDeptTimeBetweenOrderByDeptTime(departure, destination, startDateTime, endDateTime);
            }
        } else {
            if (depId == null && dstId == null) {
                posts = postRepository.findAllByPostTypeAndDeptTimeBetweenOrderByDeptTime(postType, startDateTime, endDateTime);
            } else if (depId == null) {
                posts = postRepository.findAllByPostTypeAndDestinationAndDeptTimeBetweenOrderByDeptTime(postType, destination, startDateTime, endDateTime);
            } else if (dstId == null) {
                posts = postRepository.findAllByPostTypeAndDepartureAndDeptTimeBetweenOrderByDeptTime(postType, departure, startDateTime, endDateTime);
            } else {
                posts = postRepository.findAllByPostTypeAndDepartureAndDestinationAndDeptTimeBetweenOrderByDeptTime(postType, departure, destination, startDateTime, endDateTime);
            }
        }

        List<PostGetResDto> resultList = posts.stream()
                .map(m -> new PostGetResDto(m))
                .collect(Collectors.toList());

        return resultList;
    }
    @Transactional
    public PostLogDetail getSinglePost(Long postId, PostGetLogDetailRequest request) {

        Optional<Post> post = postRepository.findById(postId);

        if(!post.isPresent()) {
            throw new PostNotFoundException();
        }

        boolean check = false;
        for(int i =  0; i<post.get().getJoiners().size(); i++){
            if(post.get().getJoiners().get(i).getMember().getUid().equals(request.getUid())){
                check = true;
            }
        }

        if(check == false ){
            throw new PostNoAuthorityException();
        }

        return new PostLogDetail(post.get());

    }

    @Transactional
    public PostInfoResponse joinPost(Long postId, PostJoinDto postJoinDto) {
        Post postInfo = null;
        Member memberInfo = null;

        Optional<Post> post = postRepository.findById(postId);
        if (post.isPresent()) {
            postInfo = post.get();
            if (compareMinute(LocalDateTime.now(), postInfo.getDeptTime()) == 1) {
                throw new PostTimeOverException();
            }
        } else {
            throw new PostNotFoundException();
        }

        if (postInfo.getStatus() == 2) {
            throw new PostMemberFullException();
        }

        Optional<Member> member = memberRepository.findMemberByUid(postJoinDto.getUid());
        if (member.isPresent()) {
            memberInfo = member.get();
        } else {
            throw new MemberNotFoundException();
        }
        if (member.get().isDeleted())
            throw new MemberNotFoundException();

        Optional<Joiner> joiner = joinerRepository.findJoinerByPostAndMember(postInfo, memberInfo);
        if (!joiner.isPresent()) {
            JoinerCreateDto joinerCreateDto = new JoinerCreateDto(memberInfo, postInfo, postJoinDto.isOwner());
            joinerRepository.save(new Joiner(joinerCreateDto));
        } else {
            throw new JoinerDuplicateMemberException();
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
                throw new PostTimeOverException();
            }
        } else {
            throw new PostNotFoundException();
        }

        Optional<Member> member = memberRepository.findMemberByUid(uid);
        if (member.isPresent()) {
            memberInfo = member.get();
        } else {
            throw new MemberNotFoundException();
        }
        if (member.get().isDeleted())
            throw new MemberNotFoundException();
        Optional<Joiner> joiner = joinerRepository.findJoinerByPostAndMember(postInfo, memberInfo);
        int joinerSize = postInfo.getJoiners().size();
        boolean checkOwner = false;
        if (joiner.isPresent()) {
            Joiner joinerInfo = joiner.get();
            if(joinerInfo.isOwner()) checkOwner = true;
            joinerInfo.setStatus(0);
            joinerInfo.setDeleted(true);
            joinerRepository.save(joinerInfo);
            System.out.println(joinerSize);

            if (joinerSize == 1){
                postInfo.setStatus(0);
                postInfo.setDeleted(true);
            }  else if (1 < joinerSize && joinerSize <= postInfo.getCapacity()) {
                postInfo.setStatus(1);
            }
            postRepository.save(postInfo);
            if (joinerSize > 1 && checkOwner) {
                joinerBeOwner = postInfo.getJoiners().get(1);
                joinerBeOwner.setOwner(true);
                joinerRepository.save(joinerBeOwner);
                newOwner = joinerBeOwner.getMember();
            }
            else{
                newOwner = joinerInfo.getMember();
            }


        } else {
            throw new JoinerNotFoundException();
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
                throw new PostTimeOverException();
            }
        } else {
            throw new PostNotFoundException();
        }

        Optional<Member> member = memberRepository.findMemberByUid(dto.getUid());
        if (member.isPresent()) {
            memberInfo = member.get();
        } else {
            throw new MemberNotFoundException();
        }
        if (member.get().isDeleted())
            throw new MemberNotFoundException();

        long checkChangeMinutes = ChronoUnit.MINUTES.between(LocalDateTime.now(), postInfo.getDeptTime());
        if (checkChangeMinutes < 3) {
            throw new PostBadDeptTimeToChangeException();
        }

        long minutes = ChronoUnit.MINUTES.between(postInfo.getDeptTime(), dto.getDeptTime());
        if (minutes >= 30) {
            throw new PostBadDeptTimeException();
        }

        Optional<Joiner> joiner = joinerRepository.findJoinerByPostAndMember(postInfo, memberInfo);
        if (joiner.isPresent()) {
            Joiner joinerInfo = joiner.get();
            if (joinerInfo.isOwner()) {
                postInfo.setDeptTime(dto.getDeptTime());
                postRepository.save(postInfo);
            } else {
                throw new JoinerNotOwnerException();
            }
        } else {
            throw new JoinerNotFoundException();
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
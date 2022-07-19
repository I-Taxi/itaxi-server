package com.itaxi.server.post.presentation;

import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import com.itaxi.server.docs.ApiDoc;
import com.itaxi.server.place.domain.repository.PlaceRepository;
import com.itaxi.server.post.application.PostService;
import com.itaxi.server.post.domain.repository.PostRepository;
import com.itaxi.server.post.domain.Post;
import com.itaxi.server.place.domain.Place;
import com.itaxi.server.post.application.PostDto;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;
import com.itaxi.server.post.application.dto.PostJoinDto;
import com.itaxi.server.post.presentation.request.PostExitRequest;
import com.itaxi.server.post.presentation.request.PostJoinRequest;
import com.itaxi.server.post.presentation.response.PostInfoResponse;

import com.itaxi.server.post.domain.dto.PostLog;
import com.itaxi.server.post.domain.dto.PostLogDetail;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.ApiOperation;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import javax.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Sort;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post")
public class PostController {

    private final PostService postService;
    private final PostRepository postRepository;
    private final PlaceRepository placeRepository;

    @ApiOperation(value = ApiDoc.POST_HISTORY)
    @GetMapping(value = "history")
    public List<PostLog> getPostLog(HttpServletRequest httpServletRequest) {
        String uid = httpServletRequest.getParameter("uid");
        return postService.getPostLog(uid);
    }

    @ApiOperation(value = ApiDoc.POST_HISTORY_DETAIL)
    @GetMapping(value = "history/{postId}")
    public PostLogDetail getPostLogDetail(@PathVariable long postId) {
        return postService.getPostLogDetail(postId);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public Iterable<Post> findAllPost() {
        return postRepository.findAll();
    }

    @ApiOperation(value = ApiDoc.POST_READ)
    @RequestMapping(method = RequestMethod.GET)
    public List<PostDto.Res> getPostDto(@RequestParam("depId")final Long depId, @RequestParam("dstId")final Long dstId, @RequestParam("time")@DateTimeFormat(iso=ISO.DATE) final LocalDate time) {
        final Long departureId = depId;
        final Place departure = placeRepository.getById(depId);
        final Place destination = placeRepository.getById(dstId);
        final LocalDateTime startDateTime = (time == LocalDate.now())? LocalDateTime.of(time, LocalTime.now()):LocalDateTime.of(time, LocalTime.of(0, 0, 0));
        final LocalDateTime endDateTime = LocalDateTime.of(time, LocalTime.of(23, 59, 59));
        List<PostDto.Res> resultList = (postRepository.findAllByDepartureAndDestinationAndDeptTimeBetweenOrderByDeptTime(departure, destination, startDateTime, endDateTime)).stream()
                .map(m -> new PostDto.Res(m))
                .collect(Collectors.toList());
        return resultList;
    }

    @ApiOperation(value = ApiDoc.POST_CREATE)
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public Post create(@RequestBody final PostDto.AddPostReq dto) {
        final Place departure = placeRepository.getById(dto.getDepId());
        final Place destination = placeRepository.getById(dto.getDstId());
        PostDto.AddPostPlaceReq postPlaceDto = new PostDto.AddPostPlaceReq(dto, departure, destination);
        PostDto.Res result = new PostDto.Res(postService.create(postPlaceDto));
        PostJoinDto joinDto= new PostJoinDto(dto.getUid(), dto.getStatus(), dto.getLuggage());
        Post postInfo = postService.joinPost(result.getId(), joinDto);
        return postInfo;
    }

    @PostMapping("/{postId}/join")
    @ApiOperation(value = ApiDoc.JOIN_POST)
    public ResponseEntity<PostInfoResponse> joinPost(@PathVariable Long postId, @RequestBody PostJoinRequest request) {
        Post postInfo = postService.joinPost(postId, PostJoinDto.from(request));
        PostInfoResponse result = postService.readPost(postInfo);

        return ResponseEntity.ok(result);
    }

    @PutMapping("/{postId}/join")
    @ApiOperation(value = ApiDoc.EXIT_POST)
    public ResponseEntity<String> exitPost(@PathVariable Long postId, @RequestBody PostExitRequest request) {
        String result = postService.exitPost(postId, request.getUid());

        return ResponseEntity.ok(result);
    }
}
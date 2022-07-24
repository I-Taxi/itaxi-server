package com.itaxi.server.post.presentation;

import org.springframework.http.HttpStatus;
import com.itaxi.server.docs.ApiDoc;
import com.itaxi.server.place.application.PlaceService;
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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import com.itaxi.server.member.domain.dto.MemberUidDTO;

import org.springframework.data.domain.Sort;
import java.util.stream.Stream;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post")
public class PostController {

    private final PostService postService;
    private final PlaceService placeService;
    private final PostRepository postRepository;
    private final PlaceRepository placeRepository;

    @ApiOperation(value = ApiDoc.POST_HISTORY)
    @GetMapping(value = "history")
    public List<PostLog> getPostLog(@RequestBody MemberUidDTO memberUidDTO) {
        return postService.getPostLog(memberUidDTO.getUid());
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
    public List<PostDto.Res> getPostDto(@RequestParam(value = "depId", required = false)final Long depId, @RequestParam(value = "dstId", required = false)final Long dstId, @RequestParam(value = "time")@DateTimeFormat(iso=ISO.DATE) final LocalDate time, @RequestParam(value = "postType", required = false)final Integer postType) {
        final Place departure = (depId == null) ? null : placeRepository.getById(depId);
        final Place destination = (dstId == null) ? null : placeRepository.getById(dstId);
        final LocalDateTime startDateTime = (time == LocalDate.now())? LocalDateTime.of(time, LocalTime.now()):LocalDateTime.of(time, LocalTime.of(0, 0, 0));
        final LocalDateTime endDateTime = LocalDateTime.of(time, LocalTime.of(23, 59, 59));


        List<PostDto.Res> resultList = null;

        return resultList;
    }

    @ApiOperation(value = ApiDoc.POST_CREATE)
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<PostInfoResponse> create(@RequestBody final PostDto.AddPostReq dto) {
        final Place departure = placeRepository.getById(dto.getDepId());
        final Place destination = placeRepository.getById(dto.getDstId());
        PostDto.AddPostPlaceReq postPlaceDto = new PostDto.AddPostPlaceReq(dto, departure, destination);
        PostDto.Res result = new PostDto.Res(postService.create(postPlaceDto));
        PostJoinDto joinDto= new PostJoinDto(dto.getUid(), dto.getStatus(), dto.getLuggage(), true);
        PostInfoResponse response = postService.joinPost(result.getId(), joinDto);
        placeService.updateView(dto.getDepId());
        placeService.updateView(dto.getDstId());

        return ResponseEntity.ok(response);
    }

    @PostMapping("/{postId}/join")
    @ApiOperation(value = ApiDoc.JOIN_POST)
    public ResponseEntity<PostInfoResponse> joinPost(@PathVariable Long postId, @RequestBody PostJoinRequest request) {
        PostInfoResponse result = postService.joinPost(postId, PostJoinDto.from(request, false));

        return ResponseEntity.ok(result);
    }

    @PutMapping("/{postId}/join")
    @ApiOperation(value = ApiDoc.EXIT_POST)
    public ResponseEntity<String> exitPost(@PathVariable Long postId, @RequestBody PostExitRequest request) {
        String result = postService.exitPost(postId, request.getUid());

        return ResponseEntity.ok(result);
    }
}
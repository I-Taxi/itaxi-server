package com.itaxi.server.post.presentation;

import com.itaxi.server.post.application.dto.AddPostDto;
import com.itaxi.server.post.application.dto.PostGetResDto;
import org.springframework.http.HttpStatus;
import com.itaxi.server.docs.ApiDoc;
import com.itaxi.server.place.application.PlaceService;
import com.itaxi.server.post.application.PostService;
import java.time.LocalDate;
import java.util.List;
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
import javax.transaction.Transactional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post")
public class PostController {
    private final PostService postService;
    private final PlaceService placeService;

    @ApiOperation(value = ApiDoc.POST_HISTORY)
    @PostMapping(value = "history")
    public List<PostLog> getPostLog(@RequestBody MemberUidDTO memberUidDTO) {
        return postService.getPostLog(memberUidDTO.getUid());
    }

    @ApiOperation(value = ApiDoc.POST_HISTORY_DETAIL)
    @GetMapping(value = "history/{postId}")
    public PostLogDetail getPostLogDetail(@PathVariable long postId) {
        return postService.getPostLogDetail(postId);
    }

    @ApiOperation(value = ApiDoc.POST_READ)
    @RequestMapping(method = RequestMethod.GET)
    public List<PostGetResDto> getPostDto(@RequestParam(value = "depId", required = false)final Long depId, @RequestParam(value = "dstId", required = false)final Long dstId, @RequestParam(value = "time")@DateTimeFormat(iso=ISO.DATE) final LocalDate time, @RequestParam(value = "postType", required = false)final Integer postType) {
        return postService.getPost(depId, dstId, time, postType);
    }

    @ApiOperation(value = ApiDoc.POST_CREATE)
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<PostInfoResponse> create(@RequestBody final AddPostDto dto) {
        PostInfoResponse response = postService.createPost(dto);
        placeService.updateView(dto.getDepId());
        placeService.updateView(dto.getDstId());

        return ResponseEntity.ok(response);
    }

    @Transactional
    @PostMapping("/{postId}/join")
    @ApiOperation(value = ApiDoc.JOIN_POST)
    public ResponseEntity<PostInfoResponse> joinPost(@PathVariable Long postId, @RequestBody PostJoinRequest request) {
        PostInfoResponse result = postService.joinPost(postId, PostJoinDto.from(request, false));

        return ResponseEntity.ok(result);
    }

    @Transactional
    @PutMapping("/{postId}/join")
    @ApiOperation(value = ApiDoc.EXIT_POST)
    public ResponseEntity<String> exitPost(@PathVariable Long postId, @RequestBody PostExitRequest request) {
        String result = postService.exitPost(postId, request.getUid());

        return ResponseEntity.ok(result);
    }
}
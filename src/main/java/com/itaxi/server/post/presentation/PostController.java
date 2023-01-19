package com.itaxi.server.post.presentation;

import com.itaxi.server.member.domain.Member;
import com.itaxi.server.post.application.dto.*;
import org.springframework.http.HttpStatus;
import com.itaxi.server.docs.ApiDoc;
import com.itaxi.server.post.application.PostService;
import java.time.LocalDate;
import java.util.List;

import com.itaxi.server.post.presentation.request.PostExitRequest;
import com.itaxi.server.post.presentation.request.PostJoinRequest;
import com.itaxi.server.post.presentation.response.PostInfoResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import com.itaxi.server.member.application.dto.MemberUidDTO;
import javax.transaction.Transactional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post")
public class PostController {
    private final PostService postService;

    @ApiOperation(value = ApiDoc.POST_HISTORY)
    @PostMapping(value = "history")
    public ResponseEntity<List<PostLog>> getPostLog(@RequestBody MemberUidDTO memberUidDTO) {
        return ResponseEntity.ok(postService.getPostLog(memberUidDTO.getUid()));
    }

    @ApiOperation(value = ApiDoc.POST_HISTORY_DETAIL)
    @GetMapping(value = "history/{postId}")
    public ResponseEntity<PostLogDetail> getPostLogDetail(@PathVariable long postId) {
        return ResponseEntity.ok(postService.getPostLogDetail(postId));
    }

    @ApiOperation(value = ApiDoc.POST_READ)
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<PostGetResDto>> getPostDto(@RequestParam(value = "depId", required = false)final Long depId, @RequestParam(value = "dstId", required = false)final Long dstId, @RequestParam(value = "time")@DateTimeFormat(iso=ISO.DATE) final LocalDate time, @RequestParam(value = "postType", required = false)final Integer postType) {
        return ResponseEntity.ok(postService.getPost(depId, dstId, time, postType));
    }

    @ApiOperation(value = ApiDoc.POST_CREATE)
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<PostInfoResponse> create(@RequestBody final AddPostDto dto) {
        PostInfoResponse response = postService.createPost(dto);

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
        Member result = postService.exitPost(postId, request.getUid());

        return ResponseEntity.ok(result.getName());
    }

    @Transactional
    @PutMapping("/{postId}")
    @ApiOperation(value = ApiDoc.POST_CHANGE_DEPT_TIME)
    public ResponseEntity<String> changePostTime(@PathVariable Long postId, @RequestBody PostTimeDto dto) {
        String result = postService.changePostTime(postId, dto);

        return ResponseEntity.ok(result);
    }
}
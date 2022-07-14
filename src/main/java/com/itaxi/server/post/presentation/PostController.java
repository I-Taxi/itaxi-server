package com.itaxi.server.post.presentation;

import com.itaxi.server.docs.ApiDoc;
import com.itaxi.server.post.application.PostService;

import com.itaxi.server.post.application.dto.JoinerCreateDto;
import com.itaxi.server.post.application.dto.PostJoinDto;
import com.itaxi.server.post.domain.Joiner;
import com.itaxi.server.post.domain.Post;
import com.itaxi.server.post.domain.repository.PostRepository;
import com.itaxi.server.post.presentation.request.PostExitRequest;
import com.itaxi.server.post.presentation.request.PostJoinRequest;
import com.itaxi.server.post.presentation.response.PostInfoResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.ApiOperation;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post")
public class PostController {
    private final PostService postService;

    @GetMapping
    @ApiOperation(value = ApiDoc.TEST)
    ResponseEntity<Object> aaa(){
        System.out.println("aaaa");
        return ResponseEntity.ok(null);
    }

    @PostMapping("/{postId}/join")
    @ApiOperation(value = ApiDoc.JOIN_POST)
    public ResponseEntity<PostInfoResponse> joinPost(@PathVariable Long postId, @RequestBody PostJoinRequest request) {
        Post postInfo = postService.joinPost(postId, PostJoinDto.from(request));
        PostInfoResponse result = postService.readPost(postInfo);

        return ResponseEntity.ok(result);
    }

    @PutMapping("/{postId/join")
    @ApiOperation(value = ApiDoc.EXIT_POST)
    public ResponseEntity<String> exitPost(@PathVariable Long postId, @RequestBody PostExitRequest request) {
        String result = postService.exitPost(postId, request.getUid());

        return ResponseEntity.ok(result);
    }
}
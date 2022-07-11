package com.itaxi.server.post.presentation;

import com.itaxi.server.docs.ApiDoc;
import com.itaxi.server.post.application.PostService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}

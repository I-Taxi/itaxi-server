package com.itaxi.server.post.presentation;

import com.itaxi.server.docs.ApiDoc;
import com.itaxi.server.post.application.PostService;

import com.itaxi.server.post.domain.dto.PostLog;
import com.itaxi.server.post.domain.dto.PostLogDetail;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

import lombok.RequiredArgsConstructor;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post")
public class PostController {
    private final PostService postService;

    /* CREATE */

    /* READ */
    @GetMapping(value = "history")
    public List<PostLog> getPostLog(HttpServletRequest httpServletRequest) {
        String uid = httpServletRequest.getParameter("uid");
        return postService.getPostLog(uid);
    }

    @GetMapping(value = "history/{postId}")
    public PostLogDetail getPostLogDetail(@PathVariable long postId) {
        return postService.getPostLogDetail(postId);
    }

    /* UPDATE */

    /* DELETE */

}

package com.itaxi.server.post.application;

import com.itaxi.server.post.application.dto.PostJoinDto;
import com.itaxi.server.post.domain.repository.PostRepository;

import com.itaxi.server.post.presentation.response.PostInfoResponse;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public PostInfoResponse joinPost(Long postId, PostJoinDto from) {
        // TODO : create joinPost
        PostInfoResponse result = null;

        return result;
    }

    public String exitPost(Long postId, String uid) {
        // TOTO : create exitPost
        String result = null;

        return result;
    }
}

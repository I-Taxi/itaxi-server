package com.itaxi.server.post.application;

import com.itaxi.server.post.domain.Post;
import com.itaxi.server.post.domain.repository.PostRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    public Post create(PostDto.AddPostPlaceReq dto) {
        return postRepository.save(dto.toEntity());
    }

}

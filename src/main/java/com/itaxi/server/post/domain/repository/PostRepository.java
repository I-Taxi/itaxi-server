package com.itaxi.server.post.domain.repository;

import com.itaxi.server.post.domain.Post;
import com.itaxi.server.place.domain.Place;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}

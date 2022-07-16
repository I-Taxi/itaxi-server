package com.itaxi.server.post.domain.repository;

import com.itaxi.server.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

}

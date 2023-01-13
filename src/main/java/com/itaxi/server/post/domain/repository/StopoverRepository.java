package com.itaxi.server.post.domain.repository;

import com.itaxi.server.post.domain.Post;
import com.itaxi.server.post.domain.Stopover;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StopoverRepository extends JpaRepository<Stopover, Long> {
    List<Stopover> findStopoversByPost(Post post);
}

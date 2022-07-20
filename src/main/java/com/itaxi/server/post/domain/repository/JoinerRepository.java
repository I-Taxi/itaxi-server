package com.itaxi.server.post.domain.repository;

import com.itaxi.server.member.domain.Member;
import com.itaxi.server.post.domain.Joiner;
import com.itaxi.server.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JoinerRepository extends JpaRepository<Joiner, Long> {
    Optional<Joiner> findJoinerByPostAndMember(Post post, Member member);
    List<Joiner> findJoinerByPost(Post post);
}

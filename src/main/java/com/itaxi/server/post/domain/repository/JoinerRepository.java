package com.itaxi.server.post.domain.repository;

import com.itaxi.server.member.domain.Member;
import com.itaxi.server.post.domain.Joiner;
import com.itaxi.server.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JoinerRepository extends JpaRepository<Joiner, Long> {
    // TODO : 에러나서 바디 추가함. 잘 동작하는지 확인 필요
    static Optional<Joiner> findJoinerByPostandMember(Post post, Member member) {
        return null;
    }
}

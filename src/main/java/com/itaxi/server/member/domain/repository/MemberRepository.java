package com.itaxi.server.member.domain.repository;

import com.itaxi.server.member.domain.Member;
import com.itaxi.server.member.application.dto.LoginResponse;
import com.itaxi.server.member.application.dto.MemberInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findMemberByUid(String uid);
    Optional<MemberInfo> findMemberInfoByUid(String uid);
    Optional<LoginResponse> findMemberForLoginByUid(String uid);
}
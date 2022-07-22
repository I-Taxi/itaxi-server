package com.itaxi.server.member.domain.repository;

import com.itaxi.server.member.domain.Member;
import com.itaxi.server.member.domain.dto.LoginResponse;
import com.itaxi.server.member.domain.dto.MemberInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findMemberByUidAndDeletedFalse(String uid);
    Optional<MemberInfo> findMemberInfoByUidAndDeletedFalse(String uid);
    Optional<LoginResponse> findMemberForLoginByUidAndDeletedFalse(String uid);
}
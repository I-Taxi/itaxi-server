package com.itaxi.server.member.domain.repository;

import com.itaxi.server.member.domain.Member;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}

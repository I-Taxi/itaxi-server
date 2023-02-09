package com.itaxi.server.cheaker;

import com.itaxi.server.exception.member.MemberNotFoundException;
import com.itaxi.server.member.domain.Member;
import com.itaxi.server.member.domain.repository.MemberRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminChecker {
    private final MemberRepository memberRepository;
    public boolean isAdmin(String uid) {
        Optional<Member> member = memberRepository.findMemberByUid(uid);

        if(!member.isPresent()) {
            throw new MemberNotFoundException();
        }

        if(member.get().isDeleted())
            throw new MemberNotFoundException();

        Member memberInfo = member.get();
        if (memberInfo.getName().equals("admin")) {
            return true;
        }

        return false;
    }
}

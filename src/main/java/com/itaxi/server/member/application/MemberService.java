package com.itaxi.server.member.application;

import com.itaxi.server.exception.member.MemberException;
import com.itaxi.server.exception.member.MemberNotFoundException;
import com.itaxi.server.member.domain.Member;
import com.itaxi.server.member.domain.dto.LoginResponse;
import com.itaxi.server.member.domain.dto.MemberInfo;
import com.itaxi.server.member.domain.dto.MemberJoinInfo;
import com.itaxi.server.member.domain.repository.MemberRepository;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    /* CREATE */
    public String createMember(String uid, String email, String phone, String name, String bank, String bankAddress) {
        Member member = new Member();
        member.setUid(uid);
        member.setEmail(email);
        member.setPhone(phone);
        member.setName(name);
        member.setBank(bank);
        member.setBankAddress(bankAddress);

        try {
            memberRepository.save(member);
            return "Success";
        }
        catch(MemberException e) {
            e.printStackTrace();
            return "Failed";
        }
    }

    /* READ */
    public MemberInfo getMember(String uid) {
        Optional<MemberInfo> member = memberRepository.findMemberInfoByUid(uid);
        if(member.isPresent())
            return member.get();
        throw new MemberNotFoundException(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public LoginResponse login(String uid) {
        Optional<LoginResponse> response = memberRepository.findMemberForLoginByUid(uid);
        if(response.isPresent())
            return response.get();
        throw new MemberNotFoundException(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /* UPDATE */
    public String updateMember(String uid, String phone, String bank, String bankAddress) {
        Optional<Member> member = memberRepository.findMemberByUid(uid);
        if(!member.isPresent())
            throw new MemberNotFoundException(HttpStatus.INTERNAL_SERVER_ERROR);
        Member memberInfo = member.get();
        memberInfo.setPhone(phone);
        memberInfo.setBank(bank);
        memberInfo.setBankAddress(bankAddress);

        try {
            memberRepository.save(memberInfo);
            return "Success";
        }
        catch(MemberException e) {
            e.printStackTrace();
            return "failed";
        }
    }

    /* DELETE */
    public String deleteMember(String uid) {
        Optional<Member> member = memberRepository.findMemberByUid(uid);
        if(!member.isPresent())
            throw new MemberNotFoundException(HttpStatus.INTERNAL_SERVER_ERROR);
        Member memberInfo = member.get();
        memberInfo.setDeleted(true);

        try {
            memberRepository.save(memberInfo);
            return "Success";
        }
        catch(MemberException e) {
            e.printStackTrace();
            return "Failed";
        }
    }
}

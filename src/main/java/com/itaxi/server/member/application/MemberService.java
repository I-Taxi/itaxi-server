package com.itaxi.server.member.application;

import com.itaxi.server.exception.member.*;
import com.itaxi.server.member.domain.Member;
import com.itaxi.server.member.domain.dto.LoginResponse;
import com.itaxi.server.member.domain.dto.MemberCreateRequestDTO;
import com.itaxi.server.member.domain.dto.MemberInfo;
import com.itaxi.server.member.domain.dto.MemberUpdateRequestDTO;
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
    public String createMember(MemberCreateRequestDTO memberCreateRequestDTO) {
        try {
            memberRepository.save(new Member(memberCreateRequestDTO));
            return "Success";
        }
        catch(MemberException e) {
            if(e instanceof MemberUidNullException)
                throw new MemberUidNullException(HttpStatus.INTERNAL_SERVER_ERROR);
            else if(e instanceof MemberEmailNullException)
                throw new MemberEmailNullException(HttpStatus.INTERNAL_SERVER_ERROR);
            else if(e instanceof MemberPhoneNullException)
                throw new MemberPhoneNullException(HttpStatus.INTERNAL_SERVER_ERROR);
            else if(e instanceof MemberNameNullException)
                throw new MemberNameNullException(HttpStatus.INTERNAL_SERVER_ERROR);
            else
                throw new MemberCreateFailedException(HttpStatus.INTERNAL_SERVER_ERROR);
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
    public String updateMember(MemberUpdateRequestDTO memberUpdateRequestDTO) {
        Optional<Member> member = memberRepository.findMemberByUid(memberUpdateRequestDTO.getUid());
        if(!member.isPresent())
            throw new MemberNotFoundException(HttpStatus.INTERNAL_SERVER_ERROR);

        Member memberInfo = member.get();
        if(memberUpdateRequestDTO.getPhone() != null)
            memberInfo.setPhone(memberUpdateRequestDTO.getPhone());
        if(memberUpdateRequestDTO.getBank() != null)
            memberInfo.setBank(memberUpdateRequestDTO.getBank());
        if(memberUpdateRequestDTO.getBankAddress() != null)
            memberInfo.setBankAddress(memberUpdateRequestDTO.getBankAddress());

        try {
            memberRepository.save(memberInfo);
            return "Success";
        }
        catch(MemberException e) {
            throw new MemberUpdateFailedException(HttpStatus.INTERNAL_SERVER_ERROR);
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
            throw new MemberDeleteFailedException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

package com.itaxi.server.member.application;

import com.itaxi.server.exception.member.*;
import com.itaxi.server.member.domain.Member;
import com.itaxi.server.member.application.dto.LoginResponse;
import com.itaxi.server.member.application.dto.MemberCreateRequestDTO;
import com.itaxi.server.member.application.dto.MemberInfo;
import com.itaxi.server.member.application.dto.MemberUpdateRequestDTO;
import com.itaxi.server.member.domain.repository.MemberRepository;

import com.itaxi.server.post.application.PostService;
import com.itaxi.server.post.application.dto.PostLog;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PostService postService;

    /* CREATE */
    @Transactional
    public String createMember(MemberCreateRequestDTO memberCreateRequestDTO) {

        if (memberCreateRequestDTO.getName().equals("admin")) {
            Optional<Member> member = memberRepository.findMemberByName(memberCreateRequestDTO.getName());

            if (!member.isPresent()) {
                memberRepository.save(new Member(memberCreateRequestDTO));
                return "Success";
            }
            else if (member.isPresent() && !member.get().isDeleted()) {
                throw new MemberDuplicateAdminException();
            }

            memberRepository.save(new Member(memberCreateRequestDTO));
            return "Success";
        }


        List<Member> memberList = memberRepository.findAll();

        if (memberList.size() != 0) {
            for (int i = 0; i < memberList.size(); i++) {
                if (memberList.get(i).getEmail().equals(memberCreateRequestDTO.getEmail()) && memberList.get(i).isDeleted()) {
                    Optional<Member> reMember = memberRepository.findMemberByUid(memberList.get(i).getUid());

                    if (reMember.isPresent()) {
                        reMember.get().setDeleted(false);
                        reMember.get().setName(memberCreateRequestDTO.getName());
                        reMember.get().setUid(memberCreateRequestDTO.getUid());
                        reMember.get().setPhone(memberCreateRequestDTO.getPhone());
                        memberRepository.save(reMember.get());
                        return "Success";
                    }
                }
            }
        }

        memberRepository.save(new Member(memberCreateRequestDTO));

        return "Success";
    }

    /* READ */
    @Transactional
    public MemberInfo getMember(String uid) {
        Optional<Member> checkMember = memberRepository.findMemberByUid(uid);
        if(!checkMember.isPresent()){
            throw new MemberNotFoundException();
        }
        if (checkMember.get().isDeleted())
            throw new MemberNotFoundException();

        Optional<MemberInfo> member = memberRepository.findMemberInfoByUid(uid);
        return member.get();

    }

    @Transactional
    public LoginResponse login(String uid) {
        Optional<Member> checkMember = memberRepository.findMemberByUid(uid);
        if(!checkMember.isPresent()) throw new MemberNotFoundException();
        if(checkMember.get().isDeleted()){
            throw new MemberNotFoundException();
        }
        Optional<LoginResponse> response = memberRepository.findMemberForLoginByUid(uid);

        if(response.isPresent())
            return response.get();
        throw new MemberNotFoundException();
    }

    /* UPDATE */
    @Transactional
    public String updateMember(MemberUpdateRequestDTO memberUpdateRequestDTO) {
        Optional<Member> member = memberRepository.findMemberByUid(memberUpdateRequestDTO.getUid());

        if(!member.isPresent())
            throw new MemberNotFoundException();
        if (member.get().isDeleted())
            throw new MemberNotFoundException();

        Member memberInfo = member.get();
        if(memberUpdateRequestDTO.getPhone() != null)
            memberInfo.setPhone(memberUpdateRequestDTO.getPhone());

        try {
            memberRepository.save(memberInfo);
            return "Success";
        }
        catch(MemberException e) {
            throw new MemberUpdateFailedException();
        }
    }

    /* DELETE */
    @Transactional
    public String deleteMember(String uid) {
        Optional<Member> member = memberRepository.findMemberByUid(uid);
        if(!member.isPresent())
            throw new MemberNotFoundException();
        if (member.get().isDeleted())
            throw new MemberNotFoundException();

        Member memberInfo = member.get();
        boolean isAvailable = true; // 삭제가능 여부

        List<PostLog> memberLogs = postService.getPostLog(uid);
        for (PostLog log : memberLogs) {
            if (log.getStatus() == 1 || log.getStatus() == 2)
                isAvailable = false;
        }

        if (isAvailable) {
            memberInfo.setDeleted(true);
            try {
                memberRepository.save(memberInfo);
                return "Success";
            }
            catch(MemberException e) {
                throw new MemberDeleteFailedException();
            }
        } else {
            throw new MemberConstraintViolationException();
        }
    }
}

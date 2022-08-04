package com.itaxi.server.member.presentation;

import com.itaxi.server.docs.ApiDoc;
import com.itaxi.server.member.application.MemberService;
import com.itaxi.server.member.domain.dto.*;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/member")
public class MemberController {

    private final MemberService memberService;

    /* CREATE */
    @ApiOperation(value = ApiDoc.MEMBER_CREATE)
    @PostMapping(value = "")
    public String createMember(@RequestBody MemberCreateRequestDTO memberCreateRequestDTO) {
        return memberService.createMember(memberCreateRequestDTO);
    }

    /* READ */
    @ApiOperation(value = ApiDoc.MEMBER_READ)
    @PostMapping(value = "/info")
    public MemberInfo getMember(@RequestBody MemberUidDTO memberUidDTO) {
        return memberService.getMember(memberUidDTO.getUid());
    }

    @ApiOperation(value = ApiDoc.MEMBER_LOGIN)
    @PostMapping(value = "/login")
    public LoginResponse login(@RequestBody MemberUidDTO memberUidDTO) {
        return memberService.login(memberUidDTO.getUid());
    }

    /* UPDATE */
    @ApiOperation(value = ApiDoc.MEMBER_UPDATE)
    @PatchMapping(value = "")
    public String updateMember(@RequestBody MemberUpdateRequestDTO memberUpdateRequestDTO) {
        return memberService.updateMember(memberUpdateRequestDTO);
    }

    /* DELETE */
    @ApiOperation(value = ApiDoc.MEMBER_DELETE)
    @PatchMapping(value = "/resign")
    public String deleteMember(@RequestBody MemberUidDTO memberUidDTO) {
        return memberService.deleteMember(memberUidDTO.getUid());
    }
}
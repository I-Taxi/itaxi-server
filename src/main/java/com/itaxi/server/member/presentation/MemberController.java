package com.itaxi.server.member.presentation;

import com.itaxi.server.docs.ApiDoc;
import com.itaxi.server.member.application.MemberService;
import com.itaxi.server.member.application.dto.*;

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
        String result = memberService.createMember(memberCreateRequestDTO);

        return result;
    }

    /* READ */
    @ApiOperation(value = ApiDoc.MEMBER_READ)
    @PostMapping(value = "/info")
    public MemberInfo getMember(@RequestBody MemberUidDTO memberUidDTO) {
        MemberInfo result = memberService.getMember(memberUidDTO.getUid());

        return result;
    }

    @ApiOperation(value = ApiDoc.MEMBER_LOGIN)
    @PostMapping(value = "/login")
    public LoginResponse login(@RequestBody MemberUidDTO memberUidDTO) {
        LoginResponse result =  memberService.login(memberUidDTO.getUid());

        return result;
    }

    /* UPDATE */
    @ApiOperation(value = ApiDoc.MEMBER_UPDATE)
    @PatchMapping(value = "")
    public String updateMember(@RequestBody MemberUpdateRequestDTO memberUpdateRequestDTO) {
        String result = memberService.updateMember(memberUpdateRequestDTO);

        return result;
    }

    /* DELETE */
    @ApiOperation(value = ApiDoc.MEMBER_DELETE)
    @PatchMapping(value = "/resign")
    public String deleteMember(@RequestBody MemberUidDTO memberUidDTO) {
        String result = memberService.deleteMember(memberUidDTO.getUid());
        return result;
    }
}
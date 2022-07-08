package com.itaxi.server.member.presentation;

import com.itaxi.server.member.application.MemberService;
import com.itaxi.server.member.domain.dto.LoginResponse;
import com.itaxi.server.member.domain.dto.MemberInfo;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/member")
public class MemberController {

    private final MemberService memberService;

    /* CREATE */
    @PostMapping(value = "")
    public String createMember(HttpServletRequest httpServletRequest) {
        String uid = httpServletRequest.getParameter("uid");
        String email = httpServletRequest.getParameter("email");
        String phone = httpServletRequest.getParameter("phone");
        String name = httpServletRequest.getParameter("name");
        String bank = httpServletRequest.getParameter("bank");
        String bankAddress = httpServletRequest.getParameter("bankAddress");

        return memberService.createMember(uid, email, phone, name, bank, bankAddress);
    }

    /* READ */
    @GetMapping(value = "")
    public MemberInfo getMember(HttpServletRequest httpServletRequest) {
        String uid = httpServletRequest.getParameter("uid");
        return memberService.getMember(uid);
    }

    @GetMapping(value = "/login")
    public LoginResponse login(HttpServletRequest httpServletRequest) {
        String uid = httpServletRequest.getParameter("uid");
        return memberService.login(uid);
    }

    /* UPDATE */
    @PatchMapping(value = "")
    public String updateMember(HttpServletRequest httpServletRequest) {
        String uid = httpServletRequest.getParameter("uid");
        String phone = httpServletRequest.getParameter("phone");
        String bank = httpServletRequest.getParameter("bank");
        String bankAddress = httpServletRequest.getParameter("bankAddress");

        return memberService.updateMember(uid, phone, bank, bankAddress);
    }

    /* DELETE */
    @PatchMapping(value = "/resign")
    public String deleteMember(HttpServletRequest httpServletRequest) {
        String uid = httpServletRequest.getParameter("uid");
        return memberService.deleteMember(uid);
    }
}

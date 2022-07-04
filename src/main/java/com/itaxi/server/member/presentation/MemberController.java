package com.itaxi.server.member.presentation;

import com.itaxi.server.member.application.MemberService;

import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class MemberController {
    private final MemberService memberService;
}

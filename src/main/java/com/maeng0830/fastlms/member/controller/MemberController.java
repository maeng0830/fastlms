package com.maeng0830.fastlms.member.controller;

import com.maeng0830.fastlms.member.model.MemberInput;
import com.maeng0830.fastlms.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@Controller
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/member/register")
    public String register() {

        return "member/register";
    }

    // request web -> server
    // response server -> web
    @PostMapping("/member/register")
    public String registerSubmit(HttpServletRequest request
            , MemberInput parameter) {

        boolean result = memberService.register(parameter);

        return "member/register_complete";
    }
}

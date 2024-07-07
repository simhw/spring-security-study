package com.example.security.member.ui;

import com.example.security.member.application.RegisterMemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MemberController {

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/member/login")
    public String login() {
        return "member/login";
    }


}

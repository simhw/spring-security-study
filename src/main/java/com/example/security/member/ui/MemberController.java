package com.example.security.member.ui;

import ch.qos.logback.core.model.Model;
import com.example.security.member.application.SignupRequest;
import com.example.security.member.application.SignupService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MemberController {

    private final SignupService signupService;

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/member/login")
    public String login() {
        return "member/login";
    }

    @GetMapping("/member/signup")
    public String signup() {
        return "member/signup";
    }

    @PostMapping("/member/signup")
    public String signup(SignupRequest request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "member/signup";
        }

        log.info("signup request: {}", request);
        signupService.signup(request);
        return "redirect:/";
    }
}

package com.example.security.member.ui;

import com.example.security.member.application.dto.RegisterMemberRequest;
import com.example.security.member.application.RegisterMemberService;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class RegisterMemberController {

    private final RegisterMemberService registerMemberService;

    @GetMapping("/member/new")
    public String registerForm(Model model) {
        model.addAttribute("request", new RegisterMemberRequest());
        return "member/register";
    }

    @PostMapping("/member/new")
    public String register(RegisterMemberRequest request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "member/register";
        }

        registerMemberService.register(request);
        return "redirect:/";
    }
}

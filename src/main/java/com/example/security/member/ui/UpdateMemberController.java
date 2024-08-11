package com.example.security.member.ui;

import com.example.security.auth.domain.UserDetailsImpl;
import com.example.security.member.application.*;
import com.example.security.member.application.dto.UpdateMemberRequest;
import com.example.security.member.domain.Member;
import com.example.security.member.domain.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class UpdateMemberController {

    private final MemberRepository memberRepository;
    private final UpdateMemberService updateMemberService;

    @GetMapping("/update")
    public String updateForm(@AuthenticationPrincipal UserDetailsImpl userDetails, Model model) {
        Long memberId = userDetails.getId();
        Member member = memberRepository.findById(memberId)
                .orElseThrow(NoMemberException::new);

        UpdateMemberRequest request = new UpdateMemberRequest(member);
        model.addAttribute("request", request);
        return "member/update";
    }

    @PostMapping("/update")
    public String update(@AuthenticationPrincipal UserDetailsImpl userDetails, UpdateMemberRequest request) {
        Long memberId = userDetails.getId();
        request.setId(memberId);

        updateMemberService.update(request);
        return "redirect:/member/update";
    }
}

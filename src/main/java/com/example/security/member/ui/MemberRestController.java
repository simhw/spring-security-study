package com.example.security.member.ui;

import com.example.security.member.application.NoMemberException;
import com.example.security.member.domain.Member;
import com.example.security.member.domain.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class MemberRestController {

    private final MemberRepository memberRepository;

    @GetMapping("/me")
    public Member info(@AuthenticationPrincipal User user) {
        log.info("member.id: {}", user.getUsername());
        Long id = Long.valueOf(user.getUsername());

        return memberRepository.findById(id)
                .orElseThrow(NoMemberException::new);
    }
}

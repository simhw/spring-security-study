package com.example.security.member.application;

import com.example.security.member.application.dto.UpdateMemberRequest;
import com.example.security.member.domain.Member;
import com.example.security.member.domain.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateMemberService {

    private static final Logger log = LoggerFactory.getLogger(UpdateMemberService.class);
    private final MemberRepository memberRepository;

    @Transactional
    public void update(UpdateMemberRequest request) {
        Member member = memberRepository.findById(request.getId())
                .orElseThrow(NoMemberException::new);

        member.update(request.getName(), request.getAddress());
    }
}

package com.example.security.member.application;

import com.example.security.auth.domain.OAuth2UserInfo;
import com.example.security.member.domain.Member;
import com.example.security.member.domain.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RegisterOAuthMemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public Member register(OAuth2UserRequest userRequest, OAuth2UserInfo oAuth2UserInfo) {
        Member member = memberRepository.findByEmail(oAuth2UserInfo.getEmail())
                .orElse(null);

        if (member == null) {
            member = new Member(
                    userRequest.getClientRegistration().getRegistrationId(),
                    oAuth2UserInfo.getId(),
                    oAuth2UserInfo.getEmail(),
                    oAuth2UserInfo.getName()
            );

            memberRepository.save(member);
        }

        return member;
    }
}

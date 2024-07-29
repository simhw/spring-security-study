package com.example.security.auth.application;

import com.example.security.auth.application.domain.OAuth2UserInfo;
import com.example.security.member.domain.Member;
import com.example.security.member.domain.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class CustOAuth2UserService extends DefaultOAuth2UserService {

    private final MemberRepository memberRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfo.of(userRequest, oAuth2User);

        Member member = memberRepository.findByEmail(oAuth2UserInfo.getEmail()).orElse(null);

        // 회원가입
        if (member == null) {
            member = save(userRequest, oAuth2UserInfo);
        }

        return new UserDetailsImpl(member, oAuth2User.getAttributes());
    }

    public Member save(OAuth2UserRequest userRequest, OAuth2UserInfo oAuth2UserInfo) {
        Member member = new Member(
                userRequest.getClientRegistration().getRegistrationId(),
                oAuth2UserInfo.getId(),
                oAuth2UserInfo.getEmail(),
                oAuth2UserInfo.getName()
        );

        memberRepository.save(member);
        return member;
    }
}

package com.example.security.auth.application;

import com.example.security.auth.domain.OAuth2UserInfo;
import com.example.security.auth.domain.UserDetailsImpl;
import com.example.security.member.application.RegisterOAuthMemberService;
import com.example.security.member.domain.Member;
import lombok.RequiredArgsConstructor;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustOAuth2UserService extends DefaultOAuth2UserService {

    private final RegisterOAuthMemberService registerOAuthMemberService;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfo.of(userRequest, oAuth2User);

        Member member = registerOAuthMemberService.register(userRequest, oAuth2UserInfo);
        return new UserDetailsImpl(member, oAuth2User.getAttributes());
    }
}

package com.example.security.auth.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Map;

@Data
@AllArgsConstructor
public class OAuth2UserInfo {

    private String id;
    private String email;
    private String name;

    public static OAuth2UserInfo of(OAuth2UserRequest userRequest, OAuth2User oAuth2User) {
        return switch (userRequest.getClientRegistration().getRegistrationId()) {
            case "google" -> convertAttributeToGoogleUserInfo(oAuth2User.getAttributes());
            case "naver" -> convertAttributeToNaverUserInfo(oAuth2User.getAttributes());
            default -> throw new IllegalArgumentException("invalid provider");
        };
    }

    public static OAuth2UserInfo convertAttributeToGoogleUserInfo(Map<String, Object> attributes) {
        String username = (String) attributes.getOrDefault("sub", null);
        String email = (String) attributes.getOrDefault("email", null);
        String name = (String) attributes.getOrDefault("name", null);
        return new OAuth2UserInfo(username, email, name);
    }

    public static OAuth2UserInfo convertAttributeToNaverUserInfo(Map<String, Object> attributes) {
        Map<String, Object> response = (Map) attributes.getOrDefault("response", Map.of());

        String username = (String) response.getOrDefault("id", null);
        String email = (String) response.getOrDefault("email", null);
        String name = (String) response.getOrDefault("name", null);
        return new OAuth2UserInfo(username, email, name);
    }
}

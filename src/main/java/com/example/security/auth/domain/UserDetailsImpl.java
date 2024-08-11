package com.example.security.auth.domain;

import com.example.security.member.domain.Member;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Getter
public class UserDetailsImpl implements UserDetails, OAuth2User {
    private final Long id;
    private final String username;
    private final String password;
    private final List<GrantedAuthority> authorities;

    private Map<String, Object> attributes;

    public UserDetailsImpl(Member member) {
        this.id = member.getId();
        this.username = member.getEmail();
        this.password = member.getPassword();
        this.authorities = new ArrayList<>();
    }

    public UserDetailsImpl(Member member, Map<String, Object> attributes) {
        this.id = member.getId();
        this.username = member.getEmail();
        this.password = member.getPassword();
        this.authorities = new ArrayList<>();
        this.attributes = attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority("ROLE_USER");
        this.authorities.add(simpleGrantedAuthority);
        return this.authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }

    @Override
    public Map<String, Object> getAttributes() {
        return this.attributes;
    }

    @Override
    public String getName() {
        return this.username;
    }
}

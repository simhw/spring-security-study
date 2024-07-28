package com.example.security.member.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import org.hibernate.annotations.Comment;

@Entity
@Getter
public class Member {
    @Id
    @GeneratedValue
    private Long id;

    @Comment("이메일")
    @Column(nullable = false, unique = true)
    private String email;

    @Comment("비밀번호")
    private String password;

    @Comment("OAuth 공급자")
    private String oauthProvider;

    @Comment("OAuth 아이디")
    private String oauthId;

    private String name;

    private String address;

    public Member() {
    }

    public Member(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public Member(String email, String oauthProvider, String oauthId, String name) {
        this.email = email;
        this.oauthProvider = oauthProvider;
        this.oauthId = oauthId;
        this.name = name;
    }

    public void update(String name, String address) {
        this.name = name;
        this.address = address;
    }
}

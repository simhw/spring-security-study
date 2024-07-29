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

    private String snsType;

    private String snsId;

    private String name;

    private String address;

    public Member() {
    }

    public Member(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public Member(String snsType, String snsId, String email, String name) {
        this.snsType = snsType;
        this.snsId = snsId;
        this.email = email;
        this.name = name;
    }

    public void update(String name, String address) {
        this.name = name;
        this.address = address;
    }
}

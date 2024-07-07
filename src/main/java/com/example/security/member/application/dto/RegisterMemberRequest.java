package com.example.security.member.application.dto;

import lombok.Data;

@Data
public class RegisterMemberRequest {
    private String email;
    private String password;
    private String name;
}

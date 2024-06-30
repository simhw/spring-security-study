package com.example.security.member.application;

import lombok.Data;

@Data
public class SignupRequest {
    private String email;
    private String password;
    private String checkPassword;
    private String name;
    private String address;
}

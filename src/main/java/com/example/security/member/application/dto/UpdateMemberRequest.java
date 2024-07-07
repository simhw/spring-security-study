package com.example.security.member.application.dto;

import com.example.security.member.domain.Member;
import lombok.Data;

@Data
public class UpdateMemberRequest {
    private Long id;
    private String email;
    private String name;
    private String address;

    public UpdateMemberRequest() {
    }

    public UpdateMemberRequest(Member member) {
        this.id = member.getId();
        this.email = member.getEmail();
        this.name = member.getName();
        this.address = member.getAddress();
    }
}

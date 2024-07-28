package com.example.security.member.ui;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class EndpointAuthorizationTest {

    @Autowired
    private MockMvc mvc;

    @Test
    @WithMockUser(authorities = "USER")
    void endpointWhenUserAuthorityThenAuthorized() throws Exception {
        this.mvc.perform(get("/endpoint"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    void endpointWhenNotUserAuthorityThenForbidden() throws Exception {
        this.mvc.perform(get("/endpoint"))
                .andExpect(status().isForbidden());
    }

    @Test
    void anyWhenUnauthenticatedThenUnauthorized() throws Exception{
        this.mvc.perform(get("/any"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void endpoint() {
    }
}
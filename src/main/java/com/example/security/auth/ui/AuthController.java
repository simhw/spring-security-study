package com.example.security.auth.ui;

import com.example.security.auth.application.JwtUtil;
import com.example.security.auth.application.dto.LoginRequest;
import com.example.security.auth.domain.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;

    @PostMapping("/api/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword(), new ArrayList<>());

            Authentication authenticated = authenticationManager.authenticate(authentication);
            UserDetailsImpl userDetails = (UserDetailsImpl) authenticated.getPrincipal();

            String token = JwtUtil.generateToken(userDetails);
            return ResponseEntity.ok().body("Bearer " + token);

        } catch (AuthenticationException e) {
            return ResponseEntity.status(401).body("fail");
        }
    }
}

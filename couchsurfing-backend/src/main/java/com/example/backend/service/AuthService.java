package com.example.backend.service;

import com.example.backend.model.User;
import com.example.backend.model.dto.AuthRequest;
import com.example.backend.model.dto.AuthResponse;
import com.example.backend.model.dto.RegistrationRequest;
import com.example.backend.model.enums.Authority;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserService userService;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authManager;


    public AuthResponse register(RegistrationRequest registrationRequest) {
        User user = userService.save(
                User.builder()
                        .fullName(registrationRequest.getFirstName() + " " + registrationRequest.getLastName())
                        .email(registrationRequest.getEmail())
                        .password(passwordEncoder.encode(registrationRequest.getPassword()))
                        .dateCreated(new Date())
                        .isVerified(false)
                        .authorities(List.of(Authority.ROLE_USER, Authority.ROLE_SURFER))
                        .rating(0.)
                        .build()
        );

        String token = jwtService.generateToken(user);

        return constructResponse(token, user.getId());
    }

    public AuthResponse authenticate(AuthRequest authRequest) {
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequest.getEmail(),
                        authRequest.getPassword()
                )
        );

        User user = userService.findByUsername(authRequest.getEmail());
        String token = jwtService.generateToken(user);

        return constructResponse(token, user.getId());
    }

    private AuthResponse constructResponse(String token, String userId) {
        return AuthResponse.builder()
                .accessToken(token)
                .userId(userId)
                .build();
    }

}

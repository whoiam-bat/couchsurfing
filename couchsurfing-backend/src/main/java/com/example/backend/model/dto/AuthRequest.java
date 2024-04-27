package com.example.backend.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthRequest {
    @Email(message = "Invalid email format.")
    private String email;

    @Pattern(regexp = "^([a-zA-Z]){2,} ([a-zA-Z]){2,}$",
            message = "Login should represent your full name.")
    @Size(min = 5, message = "Login length should be at least 5 characters.")
    private String password;
}

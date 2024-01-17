package com.example.backend.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
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
public class RegistrationRequest {
    @Size(min = 2, message = "First name should be at least 2 characters long.")
    @NotBlank(message = "Invalid First name format.")
    private String firstName;

    @Size(min = 2, message = "First name should be at least 2 characters long.")
    @NotBlank(message = "Invalid Last name format.")
    private String lastName;

    @Email(message = "Invalid email format.")
    private String email;

    @Pattern(regexp = "^([a-zA-Z]){2,} ([a-zA-Z]){2,}$",
            message = "Login should represent your full name.")
    @Size(min = 5, message = "Login length should be at least 5 characters.")
    private String password;
}

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

    @Size(min = 2, message = "Last name should be at least 2 characters long.")
    @NotBlank(message = "Invalid Last name format.")
    private String lastName;

    @Email(message = "Invalid email format.")
    @NotBlank(message = "Email is mandatory")
    private String email;

    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\\\S+$).{5,20}$",
            message = "Invalid password format!\n" +
                    "Correct format: Length from 5 to 20; without spaces; at least one digit, uppercase and lowercase character.")
    private String password;
}

package com.example.backend.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Data
public class UserInfo {

    @NotBlank(message = "This section is required")
    private String aboutMe;

    @Min(value = 18, message = "You should be at least 18 years old")
    @NotBlank
    private Integer age;

    private List<String> languages;

    private String occupation;

    private String education;

    @NotBlank(message = "This section is required")
    private String location;

    private List<String> userPhotos;
}

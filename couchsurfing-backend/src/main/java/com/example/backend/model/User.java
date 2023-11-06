package com.example.backend.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.Date;
import java.util.List;

@Document(collection = "user")
@Data
@Builder
public class User {

    @Id
    private String id;

    @Indexed(unique = true)
    @Pattern(regexp = "^([a-zA-Z]){2,} ([a-zA-Z]){2,}$",
            message = "Login should represent your fullname")
    @Size(min = 5, message = "Login length should be at least 5 characters")
    private String login;

    @Indexed(unique = true)
    @Email(message = "Invalid email format")
    private String email;

    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[-_])(?=.{8,16})$",
            message = "Password should contain at leas one digit, upper and lower case character")
    @Size(min = 8, max = 16, message = "Password length should be between 8 and 16")
    private String password;

    private Date dateCreated;

    private Boolean isVerified;

    private List<Authority> authorities;

    private Double rating;

    private UserInfo userInfo;

    private UserHome userHome;

    @DocumentReference
    @ToString.Exclude
    private List<Request> requests;

    @DocumentReference
    @ToString.Exclude
    private List<Review> reviews;

}

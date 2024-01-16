package com.example.backend.model;

import com.example.backend.model.enums.Authority;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.List;

@Document(collection = "users")
@Data
@Builder
public class User implements UserDetails {

    @Id
    private String id;

    @Indexed(unique = true)
    private String login;

    @Indexed(unique = true)
    private String email;

    private String password;

    private Date dateCreated;

    private Boolean isVerified;

    private List<Authority> authorities;

    private Double rating;

    private UserInfo userInfo;

    private UserHome userHome;

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

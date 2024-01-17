package com.example.backend.model.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Authority implements GrantedAuthority {
    ROLE_OWNER,
    ROLE_ADMIN,
    ROLE_USER,
    ROLE_SURFER,
    ROLE_HOST;

    @Override
    public String getAuthority() {
        return this.name();
    }
}

package com.skolefun.config.jwt;

import com.skolefun.model.Role;

import java.util.Set;

public class JwtTokenUserData {

    private final Long userId;
    private final String username;
    private final Set<Role> roles;

    public JwtTokenUserData(Long userId, String username, Set<Role> roles) {
        this.userId = userId;
        this.username = username;
        this.roles = roles;
    }

    public Long getUserId() {
        return userId;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public String getUsername() {
        return username;
    }

}

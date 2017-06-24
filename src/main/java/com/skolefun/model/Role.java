package com.skolefun.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public enum Role {
    TEACHER,
    STUDENT,
    PARENT;

    public GrantedAuthority toSpringGrantedAuthority() {
        return new SimpleGrantedAuthority("ROLE_" + name());
    }

}

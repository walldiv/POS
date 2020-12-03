package com.posbackend.jwt.config;

import static com.posbackend.jwt.config.AuthorityConstants.*;

public enum RolesEnum {
    ROLE_USER(USER_AUTHORITIES),
    ROLE_MANAGER(MANAGER_AUTHORITIES),
    ROLE_OWNER(OWNER_AUTHORITIES);

    private String[] authorities;

    RolesEnum(String[] authorities) {
        this.authorities = authorities;
    }

    public String[] getAuthorities() {
        return authorities;
    }
}

package com.posbackend.jwt.config;

public class AuthorityConstants {
    public static final String[] USER_AUTHORITIES = {"user:read"};
    public static final String[] MANAGER_AUTHORITIES = {"user:create", "user:read", "user:update"};
    public static final String[] OWNER_AUTHORITIES = {"user:create", "user:read", "user:update", "user:delete"};

}

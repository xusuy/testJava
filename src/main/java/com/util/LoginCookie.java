package com.util;


public class LoginCookie {
    private String userId;
    private String name;
    private String username;
    private String token;
    private String tenantId;
    private boolean manager;

    public LoginCookie(String userId, String name, String username, String tenantId) {
        this.userId = userId;
        this.name = name;
        this.username = username;
        this.tenantId = tenantId;
    }

    public LoginCookie() {
        this.token = UUIDGenerator.sequentialUUIDString();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String id) {
        this.userId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public boolean isManager() {
        return manager;
    }

    public void setManager(boolean manager) {
        this.manager = manager;
    }
}

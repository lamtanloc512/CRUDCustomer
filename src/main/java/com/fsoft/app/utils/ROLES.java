package com.fsoft.app.utils;

public enum ROLES {
    ADMIN("ROLE_ADMIN"), CUSTOMER("ROLE_CUSTOMER");

    ROLES(String role) {
        this.role = role;
    }

    private String role;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}

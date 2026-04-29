package com.luanferro.reservation_api.domain.enums;

import lombok.Getter;

@Getter
public enum UserRole {

    ADMIN("admin"),
    USER("user");

    private final String role;

    UserRole(String role){
        this.role = role;
    }

    public static UserRole from(String role) {
        if (role == null) return USER;

        return switch (role.toLowerCase()) {
            case "admin" -> ADMIN;
            case "user" -> USER;
            default -> throw new IllegalArgumentException("Invalid role: " + role);
        };
    }
}

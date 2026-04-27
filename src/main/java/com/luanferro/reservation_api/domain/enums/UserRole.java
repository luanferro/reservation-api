package com.luanferro.reservation_api.domain.enums;

public enum UserRole {

    ADMIN("admin"),
    USER("user");

    private String role;

    UserRole(String role){
        this.role = role;
    }

    public String getRole(){
        return role;
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

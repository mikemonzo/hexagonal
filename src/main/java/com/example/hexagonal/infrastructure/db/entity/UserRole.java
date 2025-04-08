package com.example.hexagonal.infrastructure.db.entity;

public enum UserRole {
    USER, ADMIN;

    public static UserRole of(String role) {
        try {
            return UserRole.valueOf(role);
        } catch (IllegalArgumentException e) {
            return USER;
        }
    }
}

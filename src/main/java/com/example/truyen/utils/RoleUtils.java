package com.example.truyen.utils;

public class RoleUtils {
    public static String mapRole(int role) {
        return switch (role) {
            case 1 -> "ROLE_ADMIN";
            case 2 -> "ROLE_MODERATOR";
            case 3 -> "ROLE_USER";
            default -> "ROLE_GUEST";
        };
    }
}

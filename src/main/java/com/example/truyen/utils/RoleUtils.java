package com.example.truyen.utils;

public class RoleUtils {
    public static String mapRole(int role) {
        return switch (role) {
            case 1 -> "ROLE_USER";
            case 2 -> "ROLE_ADMIN";
            case 3 -> "ROLE_MODERATOR";
            default -> "ROLE_GUEST";
        };
    }
}

package com.example.truyen.Entity;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequest {
    @NotEmpty(message = "Thiếu username")
    private String username;

    @NotEmpty(message = "Thiếu password")
    private String password;
}

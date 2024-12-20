package com.example.truyen.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterFormDto {
    @NotEmpty(message = "Thiếu username")
    private String username;

    @NotEmpty(message = "Thiếu password")
    private String password;

    @NotEmpty(message = "Thiếu email")
    @Email(message = "Email không hợp lệ")
    private String email;

    @NotEmpty(message = "Thiếu tên hiển thị")
    private String displayName;

    @NotEmpty(message = "Thiếu xác nhận password")
    private String confirmPassword;

    public boolean isPasswordMatching() {
        return password != null && password.equals(confirmPassword);
    }
}


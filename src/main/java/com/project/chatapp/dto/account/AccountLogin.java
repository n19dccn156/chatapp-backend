package com.project.chatapp.dto.account;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public record AccountLogin(
        @Email(message = "Email không hợp lệ")
        String email,
        @Size(min = 6, message = "Mật khẩu phải từ 6 ký tự")
        String password
) {}

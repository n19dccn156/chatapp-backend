package com.project.chatapp.dto.account;

import com.project.chatapp.dto.Enum.EGender;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record AccountRegister(
        @NotEmpty(message = "Email không bỏ trống")
        @Email(message = "Email không hợp lệ")
        String email,
        @Size(min = 6, message = "Mật khẩu phải từ 6 ký tự")
        String password,
        @Size(min = 2, message = "Biệt đanh phải từ 2 ký tự")
        String nickname,
        @Enumerated(EnumType.STRING) EGender gender,
        String avatar
) {}

package com.project.chatapp.util;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PasswordUtil {

    @NonNull final PasswordEncoder passwordEncoder;

    public String encode(String password) {
        return passwordEncoder.encode(password);
    }

    public boolean matches(String password, String passwordEncode) {
        return passwordEncoder.matches(password, passwordEncode);
    }
}

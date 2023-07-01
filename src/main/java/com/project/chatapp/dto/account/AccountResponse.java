package com.project.chatapp.dto.account;

import com.project.chatapp.dto.Enum.EGender;
import com.project.chatapp.dto.Enum.ERole;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public record AccountResponse(
        Integer friendId,
        Integer accountId,
        String email,
        String nickname,
        @Enumerated(EnumType.STRING)
        EGender gender,
        String avatar,
        @Enumerated(EnumType.STRING)
        ERole role,
        String state
) {
}

package com.project.chatapp.model;

public record TokenResponse(
        Integer accountId,
        String accessToken,
        String refreshToken
) {
}

package com.project.chatapp.dto.message;

import com.project.chatapp.dto.Enum.EType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.NonNull;

import java.util.List;

public record MessageRequest (
        @NonNull Integer senderId,
        @NonNull String roomId,
        @Enumerated(EnumType.STRING)
        @NonNull EType type,
        @NonNull String text,
        @NonNull String avatar,
        List<String> files
){}

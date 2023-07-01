package com.project.chatapp.dto.message;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record MessageList (
        String messageId,
        String roomId,
        Integer senderId,
        String name,
        String avatar,
        String text,
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
        LocalDateTime createdAt
){}

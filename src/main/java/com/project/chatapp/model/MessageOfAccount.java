package com.project.chatapp.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public record MessageOfAccount(
        String roomId,
        Integer accountId,
        String name,
        String avatar,
        String type,
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
        LocalDateTime createdAt
) {}

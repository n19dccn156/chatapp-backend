package com.project.chatapp.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public record MessageList(
        String roomId,
        Integer accountId,
        String name,
        String avatar,
        String type,
        Timestamp createdAt
) {
}

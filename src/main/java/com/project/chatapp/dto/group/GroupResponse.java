package com.project.chatapp.dto.group;

public record GroupResponse(
        String groupId,
        String name,
        String avatar,
        Integer members,
        String type
) {
}

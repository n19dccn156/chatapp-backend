package com.project.chatapp.dto.group;

import com.project.chatapp.dto.account.Sender;
import lombok.NonNull;

public record GroupSave(
        @NonNull String name,
        @NonNull String avatar,
        @NonNull Integer membersId,
        @NonNull Sender sender
) {}

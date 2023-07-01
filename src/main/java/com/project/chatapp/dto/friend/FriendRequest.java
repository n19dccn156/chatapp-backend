package com.project.chatapp.dto.friend;

import com.project.chatapp.dto.Enum.EFriendState;
import lombok.NonNull;

public record FriendRequest(
        @NonNull String friendId,
        @NonNull EFriendState state
) {}

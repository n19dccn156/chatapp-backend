package com.project.chatapp.dto.friend;

import com.project.chatapp.dto.account.Sender;
import lombok.NonNull;

public record FriendSave (
        @NonNull Sender sender,
        @NonNull Integer toId
){}

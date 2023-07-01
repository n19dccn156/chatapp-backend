package com.project.chatapp.dto.group;

import com.project.chatapp.dto.account.Sender;
import lombok.NonNull;

public record GroupRequest(
        @NonNull Integer accountId,
        @NonNull Sender sender
) {}

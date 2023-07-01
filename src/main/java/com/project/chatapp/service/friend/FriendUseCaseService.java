package com.project.chatapp.service.friend;

import com.project.chatapp.dto.account.Sender;
import com.project.chatapp.dto.friend.FriendSave;
import com.project.chatapp.dto.message.MessageResponse;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class FriendUseCaseService {
    @NonNull final FriendCommandService friendCommandService;

    @Transactional
    public MessageResponse addFriend(String room, FriendSave friend) {
        return friendCommandService.addFriend(room, friend);
    }

    @Transactional
    public MessageResponse acceptFriend(String room, Sender sender) {
        return friendCommandService.acceptFriend(room, sender);
    }
    @Transactional
    public MessageResponse deleteFriend(String room, Sender sender) {
        return friendCommandService.deleteFriend(room, sender);
    }
}

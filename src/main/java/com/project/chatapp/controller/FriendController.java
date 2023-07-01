package com.project.chatapp.controller;

import com.project.chatapp.dto.account.Sender;
import com.project.chatapp.dto.friend.FriendSave;
import com.project.chatapp.dto.message.MessageResponse;
import com.project.chatapp.service.friend.FriendUseCaseService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Tag(name = "Friend", description = "Friend API")
public class FriendController {

    @NonNull final FriendUseCaseService friendService;

    @MessageMapping("/friend.add/{room}")
    @SendTo("/topic/{room}")
    public MessageResponse addFriend(@DestinationVariable String room,
                                     @Payload FriendSave friend) {
        return friendService.addFriend(room, friend);
    }

    @MessageMapping("/friend.accept/{room}")
    @SendTo("/topic/{room}")
    public MessageResponse acceptFriend(@DestinationVariable String room,
                                        @Payload Sender sender) {
        return friendService.acceptFriend(room, sender);
    }

    @MessageMapping("/friend.delete/{room}")
    @SendTo("/topic/{room}")
    public MessageResponse deleteFriend(@DestinationVariable String room,
                                        @Payload Sender sender) {
        return friendService.deleteFriend(room, sender);
    }
}

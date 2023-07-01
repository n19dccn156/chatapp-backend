package com.project.chatapp.controller;

import com.project.chatapp.dto.account.Sender;
import com.project.chatapp.dto.group.GroupRequest;
import com.project.chatapp.dto.group.GroupResponse;
import com.project.chatapp.dto.group.GroupSave;
import com.project.chatapp.dto.message.MessageResponse;
import com.project.chatapp.service.group.GroupUseCaseService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Tag(name = "Group", description = "Groups API")
public class GroupController {

    @NonNull
    final GroupUseCaseService groupService;

    @MessageMapping("/group.create/{room}")
    @SendTo("/topic/{room}")
    public MessageResponse createGroup(@DestinationVariable String room,
                                       @Payload GroupSave group) {
        return groupService.createGroup(room, group);
    }

    @MessageMapping("/group.add/{room}")
    @SendTo("/topic/{room}")
    public MessageResponse addMember(@DestinationVariable String room,
                                       @Payload GroupRequest group) {
        return groupService.addMember(room, group);
    }

    @MessageMapping("/group.kick/{room}")
    @SendTo("/topic/{room}")
    public MessageResponse kickMember(@DestinationVariable String room,
                                      @Payload GroupRequest group) {
        return groupService.kickMember(room, group);
    }

    @MessageMapping("/group.leave/{room}")
    @SendTo("/topic/{room}")
    public MessageResponse leaveGroup(@DestinationVariable String room,
                                      @Payload Sender sender) {
        return groupService.leaveGroup(room, sender);
    }
}

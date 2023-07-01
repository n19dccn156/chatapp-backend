package com.project.chatapp.controller;

import com.project.chatapp.dto.message.MessageRequest;
import com.project.chatapp.dto.message.MessageResponse;
import com.project.chatapp.service.message.MessageUseCaseService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Tag(name = "Message", description = "Message API")
public class MessageController {

    @NonNull final MessageUseCaseService messageService;

    @MessageMapping("/message/{room}")
    @SendTo("/topic/{room}")
    public MessageResponse sendMessage(@DestinationVariable String room,
                                       @Payload MessageRequest message) {
        return messageService.sendMessage(message);
    }

}

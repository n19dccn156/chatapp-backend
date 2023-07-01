package com.project.chatapp.service.message;

import com.project.chatapp.config.exception.ResourceBadRequestException;
import com.project.chatapp.dto.Enum.EType;
import com.project.chatapp.dto.message.MessageRequest;
import com.project.chatapp.dto.message.MessageResponse;
import com.project.chatapp.model.MessageOfAccount;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class MessageUseCaseService {

    @NonNull final MessageCommandService messageCommandService;
    @NonNull final MessageQueryService messageQueryService;
    @NonNull final SimpMessagingTemplate simpMessagingTemplate;

    @Transactional
    public MessageResponse save(Integer sender, String room, EType type, String text, String avatar, List<String> files) {
        return messageCommandService.save(sender, room, type, text, avatar, files);
    }

    @Transactional
    public MessageResponse sendMessage(MessageRequest message) {
        return switch (message.type()) {
            case COMPOSING -> messageCommandService.composingMessage(message);
            case TEXT -> messageCommandService.textMessage(message);
            case IMAGE -> messageCommandService.imageMessage(message);
            case FILE -> messageCommandService.fileMessage(message);
            case VOICE -> messageCommandService.voiceMessage(message);
            case VIDEO -> messageCommandService.videoMessage(message);
            case CANCEL -> messageCommandService.cancelMessage(message);
            case ACCEPT -> messageCommandService.acceptMessage(message);
            default -> throw new ResourceBadRequestException();
        };
    }

    public List<MessageOfAccount> message(Integer accountId) {
        return messageQueryService.messages(accountId);
    }

    public List<MessageResponse> message(String roomId) {
        return messageQueryService.messages(roomId);
    }

    public void pushMessage(List<Integer> accountsId) {
        accountsId.parallelStream()
                  .forEach(id -> simpMessagingTemplate.convertAndSend("/topic/"+id, UUID.randomUUID().toString()));
    }

    public MessageOfAccount messageInfo(Integer accountId, String roomId) {
        return messageQueryService.messageInfo(accountId, roomId);
    }
}

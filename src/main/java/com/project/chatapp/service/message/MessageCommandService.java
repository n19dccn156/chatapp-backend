package com.project.chatapp.service.message;

import com.project.chatapp.dto.Enum.EType;
import com.project.chatapp.dto.message.MessageMapper;
import com.project.chatapp.dto.message.MessageRequest;
import com.project.chatapp.dto.message.MessageResponse;
import com.project.chatapp.repository.database.message.MessageEntity;
import com.project.chatapp.repository.database.message.MessageRepository;
import com.project.chatapp.service.messageFile.MessageFileUseCaseService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class MessageCommandService {

    @NonNull final MessageRepository messageRepository;
    @NonNull final MessageFileUseCaseService messageFileService;
    @NonNull final MessageMapper messageMapper;

    public MessageResponse save(Integer sender, String room, EType type, String text, String avatar, List<String> files) {
        MessageEntity newMessage = MessageEntity.builder()
                .senderId(sender)
                .roomId(room)
                .type(type)
                .text(text)
                .avatar(avatar)
                .createdAt(LocalDateTime.now())
                .build();
        return messageMapper.toDto(messageRepository.save(newMessage), files);
    }

    public MessageResponse composingMessage(MessageRequest message) {
        return save(message.senderId(), message.roomId(), EType.COMPOSING, "", message.avatar(), null);
    }

    public MessageResponse textMessage(MessageRequest message) {
        return save(message.senderId(), message.roomId(), EType.TEXT, message.text(), message.avatar(), null);
    }

    public MessageResponse imageMessage(MessageRequest message) {
        MessageResponse newMessage = save(message.senderId(), message.roomId(), EType.IMAGE, "", message.avatar(), message.files());
        messageFileService.save(message.files(), newMessage.message().getMessageId());
        return newMessage;
    }

    public MessageResponse fileMessage(MessageRequest message) {
        MessageResponse newMessage = save(message.senderId(), message.roomId(), EType.FILE, "", message.avatar(), null);
        messageFileService.save(message.files(), newMessage.message().getMessageId());
        return newMessage;
    }

    public MessageResponse voiceMessage(MessageRequest message) {
        return save(message.senderId(), message.roomId(), EType.VOICE, message.text(), message.avatar(), null);
    }

    public MessageResponse videoMessage(MessageRequest message) {
        MessageEntity newMessage = MessageEntity.builder()
                .senderId(message.senderId())
                .roomId(message.roomId())
                .type(EType.VIDEO)
                .text(message.text())
                .avatar(message.avatar())
                .createdAt(LocalDateTime.now())
                .build();
        return messageMapper.toDto(newMessage, null);
    }

    public MessageResponse cancelMessage(MessageRequest message) {
        return save(message.senderId(), message.roomId(), EType.CANCEL, message.text(), message.avatar(), null);
    }

    public MessageResponse acceptMessage(MessageRequest message) {
        return save(message.senderId(), message.roomId(), EType.ACCEPT, message.text(), message.avatar(), null);
    }
}

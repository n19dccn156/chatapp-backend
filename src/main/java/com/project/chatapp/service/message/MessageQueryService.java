package com.project.chatapp.service.message;

import com.project.chatapp.dto.message.MessageResponse;
import com.project.chatapp.model.MessageList;
import com.project.chatapp.model.MessageOfAccount;
import com.project.chatapp.repository.database.message.MessageEntity;
import com.project.chatapp.repository.database.message.MessageRepository;
import com.project.chatapp.repository.database.messageFile.MessageFileEntity;
import com.project.chatapp.service.messageFile.MessageFileQueryService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.TimeZone;

@Service
@AllArgsConstructor
public class MessageQueryService {

    @NonNull final MessageRepository messageRepository;
    @NonNull final MessageFileQueryService messageFileQueryService;

    public List<MessageOfAccount> messages(Integer accountId) {
        List<Object[]> messages = messageRepository.listMessageByAccountId(accountId);
        return messages.stream()
                        .map(data -> new MessageList((String) data[0], (Integer) data[1], (String) data[2], (String) data[3], (String) data[4], (Timestamp) data[5]))
                        .map(data -> new MessageOfAccount(data.roomId(), data.accountId(), data.name(), data.avatar(), data.type(), LocalDateTime.ofInstant(data.createdAt().toInstant(), TimeZone.getDefault().toZoneId())))
                        .toList();
    }

    public List<MessageResponse> messages(String roomId) {
        List<MessageEntity> messages = messageRepository.findAllByRoomId(roomId);
        List<MessageFileEntity> files = messageFileQueryService.findAll();
        return messages.stream().map(message -> {
            List<String> newFiles = files.stream()
                                        .filter(file -> file.getMessageId()
                                        .equals(message.getMessageId()))
                                        .map(MessageFileEntity::getUrl)
                                        .toList();
            return new MessageResponse(message, newFiles);
        }).toList();
    }

    public MessageOfAccount messageInfo(Integer accountId, String roomId) {
        return messages(accountId).stream().filter(data -> data.roomId().equals(roomId)).toList().get(0);
    }
}

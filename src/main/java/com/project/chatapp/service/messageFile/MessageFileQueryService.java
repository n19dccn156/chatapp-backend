package com.project.chatapp.service.messageFile;

import com.project.chatapp.repository.database.messageFile.MessageFileEntity;
import com.project.chatapp.repository.database.messageFile.MessageFileRepository;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MessageFileQueryService {

    @NonNull final MessageFileRepository messageFileRepository;

    public List<MessageFileEntity> findAll() {
        return messageFileRepository.findAll();
    }

    public List<String> imagesInRoom(String room) {
        return messageFileRepository.imagesInRoom(room);
    }
}

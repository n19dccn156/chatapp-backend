package com.project.chatapp.service.messageFile;

import com.project.chatapp.repository.database.messageFile.MessageFileEntity;
import com.project.chatapp.repository.database.messageFile.MessageFileRepository;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MessageFileCommandService {

    @NonNull final MessageFileRepository messageFileRepository;

    public void save(List<String> files, Integer messageId) {
        files.forEach(file -> messageFileRepository.save(new MessageFileEntity(null, messageId, file)));
    }
}

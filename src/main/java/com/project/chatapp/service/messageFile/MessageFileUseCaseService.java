package com.project.chatapp.service.messageFile;

import com.project.chatapp.dto.messageFile.MessageFileRequest;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MessageFileUseCaseService {

    @NonNull final MessageFileCommandService messageFileCommandService;
    @NonNull final MessageFileQueryService messageFileQueryService;

    public void save(List<String> files, Integer messageId) {
        messageFileCommandService.save(files, messageId);
    }

    public List<String> imagesInRoom(String room) {
        return messageFileQueryService.imagesInRoom(room);
    }
}

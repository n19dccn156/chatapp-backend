package com.project.chatapp.dto.message;

import com.project.chatapp.dto.messageFile.MessageFileRequest;
import com.project.chatapp.repository.database.message.MessageEntity;
import com.project.chatapp.repository.database.messageFile.MessageFileEntity;

import java.time.LocalDateTime;
import java.util.List;

public record MessageResponse(
   MessageEntity message,
   List<String> files
) {}
